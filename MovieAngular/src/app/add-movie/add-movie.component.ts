import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';  
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';   

@Component({
  selector: 'app-add-movie',
  standalone: true,  
  imports: [CommonModule, ReactiveFormsModule,HttpClientModule],  
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent {
  addMovieForm: any;

  
  constructor(private fb: FormBuilder, private router: Router,private http: HttpClient) {
    this.addMovieForm = this.fb.group({
      name: ['', [Validators.required]], 
      yearOfPremiere: [null, [Validators.required]], 
    });
  }

  onSubmit(): void {
    if (this.addMovieForm.valid) {
      const newMovie = this.addMovieForm.value;
    
      this.http.post('/api/movies', newMovie)
        .subscribe({
          next: (response) => {
            this.router.navigate(['/app-movies']);
          }}
        );
    }
  }
}
