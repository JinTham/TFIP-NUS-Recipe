import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RecipeService } from '../services/recipe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recipe-add',
  templateUrl: './recipe-add.component.html',
  styleUrls: ['./recipe-add.component.css']
})
export class RecipeAddComponent implements OnInit {

  form!:FormGroup
  ingredientsArr!:FormArray

  constructor(private fb:FormBuilder, private recipeSvc:RecipeService, private router:Router){ }

  ngOnInit(): void {
    this.form = this.createForm();
    this.ingredientsArr = this.form.get('ingredientsArr') as FormArray;
  }

  createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      instruction: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      ingredientsArr: this.fb.array([''], [Validators.required])
    });
  }

  processForm() {
    const recipe = this.form.value
    this.recipeSvc.postRecipe(recipe)
    this.recipeSvc.getAllRecipes()
    this.router.navigate(['/'])
  }

  addIngredient(): void {
    this.ingredientsArr.push(this.fb.control(''));
  }

  removeIngredient(idx: number): void {
    this.ingredientsArr.removeAt(idx);
  }

}
