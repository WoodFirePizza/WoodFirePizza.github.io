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
    public class DrinkController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.DRINKs != null)
            {
                return Ok(db.DRINKs);
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
                DRINK d = db.DRINKs.First(drink => drink.Drink_ID == id);
                return Ok(d);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]DRINK tDrink)
        {
            DRINK d;
            try
            {
                d = db.DRINKs.First(drink => drink.Drink_ID == id);

                d.Menu_ID = tDrink.Menu_ID;
                d.Size = tDrink.Size;
                db.SaveChanges();
                return Ok(d);
            }
            catch (Exception e)
            {
                try
                {
                    db.DRINKs.Add(tDrink);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tDrink);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]DRINK tDrink)
        {
            if (tDrink != null)
            {
                db.DRINKs.Add(tDrink);
                db.SaveChanges();
                DRINK tempDrink = db.DRINKs.First(drink => drink.Menu_ID == tDrink.Menu_ID
                                                            && drink.Size == tDrink.Size);
                return Created(new Uri(Request.RequestUri + "/" + tempDrink.Drink_ID), tempDrink);
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
                DRINK d = db.DRINKs.First(drink => drink.Drink_ID == id);
                db.DRINKs.Remove(d);
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
