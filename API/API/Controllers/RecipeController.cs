using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using API.Models;
using System.Web.Http.Cors;

namespace API.Controllers
{

    [EnableCors(origins: "*", methods: "*", headers: "*", exposedHeaders: "Location")]
    public class RecipeController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();


        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.RECIPEs != null)
            {
                return Ok(db.RECIPEs);
            }
            else
            {
                return NotFound();
            }
        }

        [HttpGet]
        public IHttpActionResult Get(string id)
        {
            try
            {
                RECIPE r = db.RECIPEs.First(recipe => recipe.ID == id);
                return Ok(r);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpGet]
        [Route("api/recipe/food/{foodID}")]
        public IHttpActionResult RecipeByFoodID(string foodID)
        {
            try
            {
                IQueryable<RECIPE> r = db.RECIPEs.Where(recipe => recipe.Food_ID == foodID);
                return Ok(r);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]RECIPE tRecipe)
        {
            RECIPE re;
            try
            {
                re = db.RECIPEs.First(recipe => recipe.ID == id);
                re.Ingredient_ID = tRecipe.Ingredient_ID;
                re.Food_ID = tRecipe.Food_ID;
                db.SaveChanges();
                return Ok(re);
            }
            catch (Exception e)
            {
                try
                {
                    db.RECIPEs.Add(tRecipe);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tRecipe);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]RECIPE tRecipe)
        {
            if (tRecipe != null)
            {
                db.RECIPEs.Add(tRecipe);
                db.SaveChanges();
                RECIPE tempRecipe = db.RECIPEs.First(recipe => recipe.Food_ID == tRecipe.Food_ID
                                                        && recipe.Ingredient_ID == tRecipe.Ingredient_ID);
                return Created(new Uri(Request.RequestUri + "/" + tempRecipe.Food_ID), tempRecipe);
            }
            else
            {
                return BadRequest("Invalid request body!");
            }
        }

        [HttpDelete]
        public IHttpActionResult Delete(string id)
        {
            try
            {
                RECIPE re = db.RECIPEs.First(recipe => recipe.ID == id);
                db.RECIPEs.Remove(re);
                db.SaveChanges();
                return Content(HttpStatusCode.NoContent, "Deleted - " + id.ToString());
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }
    }
}
