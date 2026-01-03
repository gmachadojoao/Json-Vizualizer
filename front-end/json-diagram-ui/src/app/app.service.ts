import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface JsonNodeDTO {
  id: string;
  key: string;
  type: string;
  value?: any;
  children?: JsonNodeDTO[];
}

@Injectable({
  providedIn: 'root'
})
export class DiagramService {

  private readonly API_URL = 'http://localhost:8080/json/parse';

  constructor(private http: HttpClient) {}

  parseJson(json: string): Observable<JsonNodeDTO> {
    return this.http.post<JsonNodeDTO>(
      this.API_URL,
      json,
      {
        headers: { 'Content-Type': 'application/json' }
      }
    );
  }
}
