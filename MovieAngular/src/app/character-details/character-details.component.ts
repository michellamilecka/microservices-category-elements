import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { HttpClient } from '@angular/common/http'; 
import { Router } from '@angular/router';
import { Location } from '@angular/common';


@Component({
  selector: 'app-character-details',
  imports: [HttpClientModule,CommonModule],
  templateUrl: './character-details.component.html',
  styleUrl: './character-details.component.css'
})
export class CharacterDetailsComponent {
  characterID:string='';
  characterData:any;
  constructor(private http: HttpClient,private route: ActivatedRoute,private router: Router,private location: Location) {}
  ngOnInit(): void {
    this.characterID = this.route.snapshot.paramMap.get('characterid') ?? '';  
    this.fetchCharacterDetails();
    
}
fetchCharacterDetails(): void {
  this.http.get<{ characterid: string, name: string,nameOfActor:string,lastNameOfActor:string,estimatedAge:number,movieTitle:string }>(`/api/characters/${this.characterID}`)
    .subscribe({
      next: (data) => {
        this.characterData = data;
      }
    });
}

goToPreviousPage():void{
  this.location.back();
}
}
