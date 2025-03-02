import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { HttpClient } from '@angular/common/http';  
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-character',
  imports: [HttpClientModule,CommonModule,ReactiveFormsModule],
  templateUrl: './update-character.component.html',
  styleUrl: './update-character.component.css'
})
export class UpdateCharacterComponent {
  movieID: string = '';
  characterID:string='';
  characterData: any;
  updateCharacterForm: FormGroup;
  constructor(private fb: FormBuilder,private route: ActivatedRoute,private http: HttpClient,private router: Router) {
      this.updateCharacterForm = this.fb.group({
        name: ['', [Validators.required]], 
        nameOfActor: ['', [Validators.required]], 
        lastNameOfActor: ['', [Validators.required]],
        estimatedAge: ['', [Validators.required]],  
      });
    }
    ngOnInit(): void {
      this.movieID = this.route.snapshot.paramMap.get('movieid') ?? '';
      this.characterID = this.route.snapshot.paramMap.get('characterid') ?? '';  
      this.fetchCharacterDetails();
    }

    fetchCharacterDetails(): void {
      this.http.get<{ characterid: string, name: string,nameOfActor:string,lastNameOfActor:string,estimatedAge:number,movieTitle:string }>(`/api/characters/${this.characterID}`)
        .subscribe({
          next: (data) => {
            this.characterData = data;
            this.updateCharacterForm.patchValue({
              name: this.characterData?.name,
              nameOfActor: this.characterData?.nameOfActor,
              lastNameOfActor: this.characterData?.lastNameOfActor,
              estimatedAge: this.characterData?.estimatedAge,
            });
          }
        });
    }

    onSubmit(): void {
      if (this.updateCharacterForm.valid) {
        const updatedCharacter = {
          name: this.updateCharacterForm.get('name')?.value,  
          nameOfActor: this.updateCharacterForm.get('nameOfActor')?.value,
          lastNameOfActor: this.updateCharacterForm.get('lastNameOfActor')?.value,
          estimatedAge: this.updateCharacterForm.get('estimatedAge')?.value

        };
    
        this.http.patch(`/api/characters/${this.characterID}`, updatedCharacter)
          .subscribe({
            next: (response) => {
              this.router.navigate([`/movie-details/${this.movieID}`]);
            }
          });
      }
    }
}
