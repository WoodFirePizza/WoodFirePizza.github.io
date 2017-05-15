using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;
using API.Models;

namespace API.Controllers
{
    [EnableCors(origins: "*", methods: "*", headers: "*", exposedHeaders: "Location")]
    public class IngredientController : ApiController
    {
        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.INGREDIENTs != null)
            {
                return Ok(db.INGREDIENTs);
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
                INGREDIENT i = db.INGREDIENTs.First(ingr => ingr.Ingredient_ID == id);
                return Ok(i);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]INGREDIENT tIngredient)
        {
            INGREDIENT i;
            try
            {
                i = db.INGREDIENTs.First(ingr => ingr.Ingredient_ID == id);
                i.Description = tIngredient.Description;
                db.SaveChanges();
                return Ok(i);
            }
            catch (Exception e)
            {
                try
                {
                    db.INGREDIENTs.Add(tIngredient);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tIngredient);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]INGREDIENT tIngredient)
        {
            if (tIngredient != null)
            {
                db.INGREDIENTs.Add(tIngredient);
                db.SaveChanges();
                INGREDIENT tempIngredient = db.INGREDIENTs.First(ingred => ingred.Description == tIngredient.Description);
                return Created(new Uri(Request.RequestUri + "/" + tempIngredient.Ingredient_ID), tempIngredient);
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
                INGREDIENT i = db.INGREDIENTs.First(ingr => ingr.Ingredient_ID == id);
                db.INGREDIENTs.Remove(i);
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
