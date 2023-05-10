import { Component, OnDestroy, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { Recipe } from '../models/recipe';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes!:Recipe[]

  constructor(private recipeSvc:RecipeService) { }

  async ngOnInit() {
      this.recipes = await this.recipeSvc.getAllRecipes()
      console.log(this.recipes)
  }

}
