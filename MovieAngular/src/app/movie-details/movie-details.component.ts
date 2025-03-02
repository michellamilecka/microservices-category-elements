import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { HttpClient } from '@angular/common/http'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-details',
  imports: [HttpClientModule,CommonModule],
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.css'
})
export class MovieDetailsComponent {
  movieID: string = '';
  title: string = '';
  movieData: any;  
  characters: { characterID: string, name: string }[] = [];
  constructor(private http: HttpClient,private route: ActivatedRoute,private router: Router) {}

  ngOnInit(): void {
    this.movieID = this.route.snapshot.paramMap.get('id') ?? '';  
    this.fetchMovieDetails();
    
}
fetchMovieDetails(): void {
  this.http.get<{ movieID: string, name: string,yearOfPremiere:number }>(`/api/movies/${this.movieID}`)
    .subscribe({
      next: (data) => {
        this.movieData = data;
        this.title = this.movieData.name;  
          this.fetchCharacterNames();
      }
    });
}
fetchCharacterNames(): void {
  
  this.http.get<{ characterID: string, name: string }[]>(`/api/movies/${this.title}/characters`)
    .subscribe({
      next: (characters) => {
        
        this.characters = characters;
      }
    });
}

removeCharacter(index: number): void {
  const characterID = this.characters[index].characterID;

  this.http.delete(`/api/characters/${characterID}`).subscribe({
    next: () => {
      this.characters.splice(index, 1);  
    }
  });
}

goToMainPage():void{
  this.router.navigate(['/app-movies']);
}

goToAddCharacter():void{
  this.router.navigate([`/add-character/${this.title}`]);
}

goToUpdateCharacter(index:number):void{
  const characterID = this.characters[index].characterID;
  this.router.navigate([`/update-character/${this.movieID}/${characterID}`])
}

goToCharacterDetails(index:number):void{
  const characterID = this.characters[index].characterID;
  this.router.navigate([`/character-details/${this.movieID}/${characterID}`])
}
}
