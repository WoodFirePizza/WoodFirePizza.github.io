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
    public class FoodController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.FOODs != null)
            {
                return Ok(db.FOODs);
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
                FOOD f = db.FOODs.First(food => food.Food_ID == id);
                return Ok(f);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]FOOD tFood)
        {
            FOOD f;
            try
            {
                f = db.FOODs.First(food => food.Food_ID == id);
                f.Menu_ID = tFood.Menu_ID;
                f.Type = tFood.Type;
                f.IsGlutenFree = tFood.IsGlutenFree;
                f.IsVegeterian = tFood.IsVegeterian;
                f.IsSpicy = tFood.IsSpicy;
                db.SaveChanges();
                return Ok(f);
            }
            catch (Exception e)
            {
                try
                {
                    db.FOODs.Add(tFood);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tFood);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]FOOD tFood)
        {
            if (tFood != null)
            {
                db.FOODs.Add(tFood);
                db.SaveChanges();
                FOOD tempFood = db.FOODs.First(food => food.Menu_ID == tFood.Menu_ID
                                                && food.Type == tFood.Type
                                                && food.IsGlutenFree == tFood.IsGlutenFree
                                                && food.IsVegeterian == tFood.IsVegeterian
                                                && food.IsSpicy == tFood.IsSpicy);
                return Created(new Uri(Request.RequestUri + "/" + tempFood.Food_ID), tempFood);
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
                FOOD f = db.FOODs.First(food => food.Food_ID == id);
                db.FOODs.Remove(f);
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
