import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent {
  productReq: Product = {id: 0, name: '', category: '', description: '', price: 0};

  constructor(private productService: ProductService, private router: Router){}

  addProduct(){
    this.productService.addProduct(this.productReq).subscribe({
      next: (res) => {
        console.log('Product added:',res);             
        this.router.navigate(['/']); 
      },
      error: (err) => {
        console.error('Error adding product:', err);
      }
  });
  }

}
