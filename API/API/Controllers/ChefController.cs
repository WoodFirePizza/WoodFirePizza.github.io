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
    public class ChefController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.CHEFs != null)
            {
                return Ok(db.CHEFs);
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
                CHEF c = db.CHEFs.First(chef => chef.Chef_ID == id);
                return Ok(c);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]CHEF tChef)
        {
            CHEF c;
            try
            {
                c = db.CHEFs.First(chef => chef.Chef_ID == id);

                c.Person_ID = tChef.Person_ID;
                c.Salary = tChef.Salary;


                db.SaveChanges();
                return Ok(c);
            }
            catch (Exception e)
            {
                try
                {
                    db.CHEFs.Add(tChef);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tChef);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]CHEF tChef)
        {
            
            if (tChef != null)
            {
                if (!(db.CHEFs.Any(chef => chef.Person_ID == tChef.Person_ID)))
                {
                    db.CHEFs.Add(tChef);
                    db.SaveChanges();
                    CHEF tempChef = db.CHEFs.First(chef => chef.Person_ID == tChef.Person_ID && chef.Salary == tChef.Salary);
                    return Created(new Uri(Request.RequestUri + "/" + tempChef.Chef_ID), tempChef);
                } else
                {
                    return BadRequest("Chef already exists!");
                }
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
                CHEF c = db.CHEFs.First(chef => chef.Chef_ID == id);
                db.CHEFs.Remove(c);
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
