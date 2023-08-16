import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {
  products: Product[] = []

  constructor(private productService: ProductService, private router: Router){}

  ngOnInit() {
    this.productService.getProductList().subscribe(data => {this.products = data;})
  }

  deleteProduct(id: number){
    this.productService.deleteProduct(id).subscribe({
      next: (res) => {
        console.log(`Product with Id: ${id} deleted successfully.`);
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error(`Error deleting product with Id: ${id}:`, err);
      }
    });
  }

}
