import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {Configuration} from "./configuration";
import {environment} from "../../environments/environment";

@Injectable()
export class ConfigurationService {

  constructor(private http: Http) { }

  getConfigurations(): Observable<Configuration[]> {
    return this.http.get(environment.backendUrl+"/configurations")
      .map(response => response.json() as Configuration[])
      .catch(this.handleError);
  }

  getConfiguration(id: number): Observable<Configuration> {
    if (! isNaN(id)) {
      return this.http.get(environment.backendUrl+"/configurations/"+id)
        .map(response => response.json() as Configuration)
        .catch(this.handleError)
    } else {
      return Observable.of<Configuration>();
    }

  }

  update(configuration: Configuration): Observable<Configuration> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(environment.backendUrl+"/configurations/"+configuration.id,JSON.stringify(configuration),options)
      .catch(this.handleError);
  }

  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
