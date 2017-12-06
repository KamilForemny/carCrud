import {Component} from '@angular/core';
import {Car} from './car';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/retry';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AUTO CRUD';
  cars: Car[];
  newCar = new Car();
  updatedCar = new Car;
  errorMsg: String;
  putError: String;
  private apiUrl = 'http://localhost:8080/cars';

  constructor(private http: HttpClient) {
    console.log('works');
    this.getCars();
  }

  getCars() {
    return this.http.get(this.apiUrl)
      .retry(2)
      .subscribe(
        data => {
          console.log(data);
          this.cars = data as Car[];
        },
        (error: HttpErrorResponse) => {
          if (error.error instanceof Error) {
            console.log('Blad:' + error.message);
          } else {
            console.log('Backend error: ' + error.status + error.message);
          }
        }
      );
  }

  createCar(car: Car) {
    this.errorMsg = null;
    return this.http
      .post<Car>(this.apiUrl, car, {headers: new HttpHeaders().set('Content-Type', 'application/json')})
      .subscribe(data => {
          this.cars.push(data as Car);
          this.getCars();
        },
        (error2: HttpErrorResponse) => {
          if (error2.error instanceof Error) {
            console.log('Blad' + error2.message);
            this.getCars();
          } else {
            console.log(error2);
            if (error2.status !== 201) { // Nie moge sobie poradzić z blędem 201 !!?? (faliture during parsing )
              this.errorMsg = error2.error as String; // to dobre jestl
              console.log(error2.error);
              this.getCars();
            }
            this.getCars();
          }
        }
      );
  }

  updateCar(car: Car, id: number) {
    this.putError = null;
    return this.http
      .put(this.apiUrl + '/' + car.id, car)
      .subscribe(value => {
          console.log(value);
          this.getCars();
        }, (error2: HttpErrorResponse) => {
          if (error2 instanceof Error) {
            console.log('Bląd: ' + error2.message);
            this.getCars();
          } else {
            console.log(error2);
            if (error2.status !== 200) {
              this.putError = error2.error as String;
              console.log(error2.error);
              this.getCars();
            }
            this.getCars();
          }
        }
      );
  }

  deleteCar(id: number) {
    return this.http.delete(this.apiUrl + '/' + id)
      .subscribe(value => {
          console.log(value);
          this.getCars();
        }
      );
  }


}

