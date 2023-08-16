import { Injectable } from '@angular/core';
import { HttpClient } from  '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  private baseUrl = 'http://localhost:8080/api/v1';

  getProductList():Observable<Product[]>{
    return this.httpClient.get<Product[]>(this.baseUrl + '/getProducts')
  }

  getProductById(id: string):Observable<Product>{
    return this.httpClient.get<Product>(this.baseUrl + '/getProductById/'+id)
  }

  addProduct(pReq: Product): Observable<Product>{
    return this.httpClient.post<Product>(this.baseUrl + '/addProduct', pReq);
  }

  updateProduct(id: number, uReq: Product): Observable<Product>{
    return this.httpClient.put<Product>(this.baseUrl + '/updateProduct/'+id, uReq);
  }

  deleteProduct(id: number):Observable<Product>{
    return this.httpClient.delete<Product>(this.baseUrl + '/deleteProduct/'+id)
  }
}
