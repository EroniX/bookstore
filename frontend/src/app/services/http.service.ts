import {Injectable} from '@angular/core';
import {Headers, Http, Request, RequestOptions, RequestOptionsArgs, Response, XHRBackend} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {AuthService} from './auth.service';

@Injectable()
export class HttpService extends Http {

  constructor(private authService: AuthService, backend: XHRBackend, options: RequestOptions) {
    super(backend, options);
    const token = authService.getEncodedToken();
    options.headers.set('Authorization', `Bearer ${token}`);
  }

  request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
    const token = this.authService.getEncodedToken();
    if (typeof url === 'string') { // meaning we have to add the token to the options, not in url
      if (!options) {
        // let's make option object
        options = {headers: new Headers()};
      }
      options.headers.set('Authorization', `Bearer ${token}`);
      options.headers.set('Content-Type', 'application/json;charset=UTF-8');

    } else {
      // we have to add the token to the url object
      url.headers.set('Authorization', `Bearer ${token}`);
      url.headers.set('Content-Type', 'application/json;charset=UTF-8');
    }
    return super.request(url, options).catch(this.catchAuthError(this));
  }

  private catchAuthError(self: HttpService) {
    // we have to pass HttpService's own instance here as `self`
    return (res: Response) => {
      console.log(res);
      if (res.status === 401 || res.status === 403) {
        // if not authenticated
        console.log(res);
      }
      return Observable.throw(res);
    };
  }
}
