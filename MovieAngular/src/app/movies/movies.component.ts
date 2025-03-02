import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';  
import { Router } from '@angular/router';

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [CommonModule,HttpClientModule],  
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent {
  movies: { movieID: string, name: string }[] = [];

  constructor(private http: HttpClient,private router: Router) {}

  fetchMovies(): void {
    this.http.get<{ movieID: string, name: string }[]>('/api/movies')  
      .subscribe({
        next: (data) => {
          this.movies = data;  
        }
      });
  }

  removeMovie(index: number): void {
    const movieName = this.movies[index].name;

    this.http.delete(`/api/movies/${movieName}`).subscribe({
      next: () => {
        this.movies.splice(index, 1);  
      }
    });
  }

  ngOnInit(): void {
    this.fetchMovies();
  }

  goToFormAddMovie(): void {
    this.router.navigate(['/add-movie']);
  }
  goToUpdateMovie(index: number): void {
    const movieID = this.movies[index].movieID;  
    this.router.navigate([`/update-movie/${movieID}`]); 
  }

  goToMovieDetails(index: number): void {
    const movieID = this.movies[index].movieID;  
    this.router.navigate([`/movie-details/${movieID}`])
  }
}
