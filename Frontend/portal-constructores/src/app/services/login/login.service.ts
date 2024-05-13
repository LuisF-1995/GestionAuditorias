import { Injectable } from '@angular/core';
import { localTokenKeyName, localUserIdKeyName } from '../../../constants/GlobalData';
import { ApiService } from '../api/api.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor( private apiService: ApiService ) {}

  async login(apiUrl:string, endpoint:string, data:{email:string, password:string}, token?:string): Promise<{success:boolean, message:string, status:number}> {
    return new Promise((resolve, reject) => {
      this.apiService.post(apiUrl, endpoint, data).subscribe({
        next: (response:{authenticationSuccess:boolean, jwtToken:string, authInfo:string, userInfo:any}) => {
          if(response.authenticationSuccess){
            localStorage.setItem(localTokenKeyName, response.jwtToken);
            localStorage.setItem(localUserIdKeyName, response.userInfo.id);
          }
          resolve({
            success: response.authenticationSuccess,
            message: response.authInfo,
            status: 200
          })
        },
        error: (error) => {
          if(error.status == 401)
            resolve({
              success: false,
              message: "Favor validar la información ingresada!!",
              status: error.status
            });
        }
      });
    })
  }

  async verifyClientExists(apiUrl:string, endpoint:string, data:{email:string, password:string}, token?:string): Promise<boolean>{
    return new Promise((resolve, reject) => {
      this.apiService.post(apiUrl, endpoint, data).subscribe({
        next: (response) => {
          resolve(true);
        },
        error: (error) => {
          resolve(false);
        }
      });
    })
  }
}
