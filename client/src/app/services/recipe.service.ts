import { HttpClient, HttpClientModule, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recipe } from '../models/recipe';
import { lastValueFrom } from 'rxjs/internal/lastValueFrom';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private httpClient:HttpClient) { }

  SB_URL_GET:string = "/api/recipes"
  SB_URL_GET_RECIPE:string = "/api/recipe"
  SB_URL_POST:string = "/api/recipe"
  headers = new HttpHeaders().set("Content-Type","application/json")

  getAllRecipes():Promise<any> {
    return lastValueFrom(this.httpClient.get<Recipe[]>(this.SB_URL_GET,{headers:this.headers}))
  }

  getRecipe(recipeId:string):Promise<any> {
    return lastValueFrom(this.httpClient.get<Recipe>(this.SB_URL_GET_RECIPE+'/'+recipeId, {headers:this.headers}))
  }

  postRecipe(recipe:Recipe):Promise<any> {
    //console.log(JSON.stringify(recipe))
    return lastValueFrom(this.httpClient.post<Recipe>(this.SB_URL_POST, JSON.stringify(recipe), {headers:this.headers}))
  }

}
