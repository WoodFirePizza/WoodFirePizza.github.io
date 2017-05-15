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
    public class DriverController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();


        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.DRIVERs != null)
            {
                return Ok(db.DRIVERs);
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
                DRIVER driv = db.DRIVERs.First(driver => driver.Driver_ID == id);
                return Ok(driv);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]DRIVER tDriver)
        {
            DRIVER driv;
            try
            {
                driv = db.DRIVERs.First(driver => driver.Driver_ID == id);
                driv.Person_ID = tDriver.Person_ID;
                driv.Salary = tDriver.Salary;
                driv.IsLoggedIn = tDriver.IsLoggedIn;
                driv.Valid_License = tDriver.Valid_License;
                driv.Vehicle_Type = tDriver.Vehicle_Type;
                db.SaveChanges();
                return Ok(driv);
            }
            catch (Exception e)
            {
                try
                {
                    db.DRIVERs.Add(tDriver);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tDriver);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]DRIVER tDriver)
        {
            if (tDriver != null)
            {

                if (!(db.DRIVERs.Any(driver => driver.Person_ID == tDriver.Person_ID)))
                {
                    db.DRIVERs.Add(tDriver);
                    db.SaveChanges();
                    DRIVER tempDriver = db.DRIVERs.First(driver => driver.Person_ID == tDriver.Person_ID
                                                                && driver.Salary == tDriver.Salary
                                                                && driver.IsLoggedIn == tDriver.IsLoggedIn
                                                                && driver.Valid_License == tDriver.Valid_License
                                                                && driver.Vehicle_Type == tDriver.Valid_License);
                    return Created(new Uri(Request.RequestUri + "/" + tDriver.Driver_ID), tDriver);
                } else
                {
                    return BadRequest("Driver already exists!");
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
                DRIVER driv = db.DRIVERs.First(driver => driver.Driver_ID == id);
                db.DRIVERs.Remove(driv);
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
