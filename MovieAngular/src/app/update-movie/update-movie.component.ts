import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { HttpClient } from '@angular/common/http';  
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'; 
import { Router } from '@angular/router';


@Component({
  selector: 'app-update-movie',
  imports: [HttpClientModule,CommonModule,ReactiveFormsModule],
  templateUrl: './update-movie.component.html',
  styleUrl: './update-movie.component.css'
})
export class UpdateMovieComponent {
  movieID: string = '';
  movieData: any;
  updateMovieForm: FormGroup;
  constructor(private fb: FormBuilder,private route: ActivatedRoute,private http: HttpClient,private router: Router) {
      this.updateMovieForm = this.fb.group({
        name: ['', [Validators.required]], 
        yearOfPremiere: ['', [Validators.required]], 
      });
    }

  ngOnInit(): void {
    this.movieID = this.route.snapshot.paramMap.get('id') ?? '';  
    this.fetchMovieDetails();
  }

  fetchMovieDetails(): void {
    this.http.get<{ movieID: string, name: string,yearOfPremiere:number }>(`/api/movies/${this.movieID}`)
      .subscribe({
        next: (data) => {
          this.movieData = data;
          this.updateMovieForm.patchValue({
            name: this.movieData.name,
            yearOfPremiere: this.movieData.yearOfPremiere
          });
        }
      });
  }
  onSubmit(): void {
    if (this.updateMovieForm.valid) {
      const updatedMovie = {
        name: this.updateMovieForm.get('name')?.value,  
        yearOfPremiere: this.updateMovieForm.get('yearOfPremiere')?.value
      };
  
      this.http.patch(`/api/movies/${this.movieID}`, updatedMovie)
        .subscribe({
          next: (response) => {
            this.router.navigate(['/app-movies']);
          }
        });
    }
  }
  
}
