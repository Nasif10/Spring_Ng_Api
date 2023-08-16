import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent {
  productDetails: Product = {id: 0, name: '', category: '', description: '', price: 0};

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router){}

  ngOnInit() { 
    this.route.paramMap.subscribe({
      next: (params) => {
        const id = params.get('id');

        if(id){
          this.productService.getProductById(id).subscribe({
            next: (res) => {this.productDetails = res}
          });
        }
      }
    })
  }

  updateProduct(){
    this.productService.updateProduct(this.productDetails.id, this.productDetails).subscribe({
      next: (res) => {
        console.log('Product updated:', res);
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Error updating product:', err);
      }
    });
  }

}
