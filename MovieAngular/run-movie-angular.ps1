docker build -t movie-angular .

docker run -d -p 8084:90 --name movie-angular movie-angular

Write-Host "Udalo sie poprawnie zbudowac image i uruchomic kontener! Otworzy sie strona w przegladarce!" -ForegroundColor DarkMagenta
Start-Sleep -Seconds 3
Start-Process "http://localhost:8084"
