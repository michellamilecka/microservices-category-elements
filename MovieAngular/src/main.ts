import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { provideRouter, RouterModule } from '@angular/router';
import { AppComponent } from './app/app.component';
import { MoviesComponent } from './app/movies/movies.component';
import { AddMovieComponent } from './app/add-movie/add-movie.component';
import { UpdateMovieComponent } from './app/update-movie/update-movie.component';
import {MovieDetailsComponent} from './app/movie-details/movie-details.component';
import { AddCharacterComponent } from './app/add-character/add-character.component';
import { UpdateCharacterComponent } from './app/update-character/update-character.component';
import { CharacterDetailsComponent } from './app/character-details/character-details.component';

type PathMatch = "full" | "prefix" | undefined;

const routes = [
  { path: '', redirectTo: '/app-movies',pathMatch: 'full' as PathMatch  }, 
  { path: 'app-movies', component: MoviesComponent },  
  { path: 'add-movie', component: AddMovieComponent },
  { path: 'update-movie/:id', component: UpdateMovieComponent },  
  { path: 'movie-details/:id', component: MovieDetailsComponent },  
  { path: 'add-character/:title', component: AddCharacterComponent },
  { path: 'update-character/:movieid/:characterid', component: UpdateCharacterComponent },
  { path: 'character-details/:movieid/:characterid', component: CharacterDetailsComponent }
];

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(withFetch()),  
    provideRouter(routes) 
  ]
})
  .catch((err) => console.error(err));
