import { Component, OnDestroy, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe.service';
import { Recipe } from '../models/recipe';
import { Subscription } from 'rxjs/internal/Subscription';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit, OnDestroy {

  recipeId!:string
  recipe!:Recipe
  sub$!:Subscription

  constructor(private recipeSvc:RecipeService, private actRoute:ActivatedRoute) { }
  
  ngOnInit(): void {
    this.sub$ = this.actRoute.params.subscribe(
      async (params) => {
        this.recipeId = params["recipeId"]
        this.recipe = await this.recipeSvc.getRecipe(this.recipeId)
      }
    )
  }

  ngOnDestroy(): void {
    this.sub$.unsubscribe()
  }

}
