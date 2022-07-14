## Uruchomienie w dockerze
Uruchamiamy za pomocą: docker-compose up lub docker-compose up -d . 
Jeśli docker jest uruchamiany np. sudo docker-compose to należy zmienić uprawniena na katalogach które powstały. Najłatwiejszy sposób będąc w katalogu aplikacji: sudo chmod -R 777 . 

## Uruchomienie w celach developerskich
Dockera uruchamiamy za pomocą docker-compose -f docker-compose-localhost.yml up
Natomiast samą aplikację uruchamiamy w Intelliju z profilem dev. 

## Przykładowe użycie 
1. Uruchamiamy kontener z aplikacją: docker-compose up
2. Uruchamiamy Swaggera http://localhost:8888/swagger-ui.html
3. Wypełniamy pole resource https://www.baeldung.com/spring-boot-kafka-testing
4. Pole typeResource wpisujemy SITE
5. Klikamy: Execute
6. Następnie uruchamiamy MongoExpress (http://localhost:8081) i sprawdzamy czy pojawiała się kolekcja resource i następnie listujemy zawartość.

