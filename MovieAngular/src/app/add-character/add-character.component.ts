import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';  
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';  
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';


@Component({
  selector: 'app-add-character',
  standalone:true,
  imports: [CommonModule, ReactiveFormsModule,HttpClientModule],
  templateUrl: './add-character.component.html',
  styleUrl: './add-character.component.css'
})
export class AddCharacterComponent {
  addCharacterForm:any;
  movieID:string='';
  movieTitle:string='';
  movieData:any;

  constructor(private fb: FormBuilder, private router: Router,private http: HttpClient,private route: ActivatedRoute,private location: Location) {
    this.addCharacterForm = this.fb.group({
      name: ['', [Validators.required]], 
      nameOfActor: ['', [Validators.required]], 
      lastNameOfActor: ['', [Validators.required]], 
      estimatedAge: [null, [Validators.required]], 
    });
  }
  ngOnInit(): void {
    this.movieTitle = this.route.snapshot.paramMap.get('title') ?? '';  
    
}
fetchMovieDetails(): void {
  this.http.get<{ movieID: string, name: string,yearOfPremiere:number }>(`/api/movies/${this.movieID}`)
    .subscribe({
      next: (data) => {
        this.movieData = data;
        this.movieTitle = this.movieData.name;  
          
      }
    });
}
  onSubmit(): void {
    if (this.addCharacterForm.valid) {
      const newCharacter = this.addCharacterForm.value;
    
      this.http.post(`/api/movies/${this.movieTitle}/characters`, newCharacter)
        .subscribe({
          next: (response) => {
            this.location.back();
          }}
        );
    }
  }
}
