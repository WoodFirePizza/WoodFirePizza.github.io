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

    [EnableCors(origins: "*", headers: "*", methods: "*", exposedHeaders: "Location")]
    public class AdminController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();
        

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.ADMINs != null)
            {
                return Ok(db.ADMINs);
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
                ADMIN ad = db.ADMINs.First(admin => admin.Admin_ID == id);
                return Ok(ad);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]ADMIN tAdmin)
        {
            ADMIN ad;
            try
            {
                ad = db.ADMINs.First(admin => admin.Admin_ID == id);
                ad.Person_ID = tAdmin.Person_ID;
                ad.Salary = tAdmin.Salary;

                db.SaveChanges();
                return Ok(ad);
            }
            catch (Exception e)
            {
                try
                {
                    db.ADMINs.Add(tAdmin);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tAdmin);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]ADMIN tAdmin)
        {
            if (tAdmin != null)
            {
                if (!(db.ADMINs.Any(admin => admin.Person_ID == tAdmin.Person_ID)))
                {
                    db.ADMINs.Add(tAdmin);
                    db.SaveChanges();
                    ADMIN tempAdmin = db.ADMINs.First(admin => admin.Person_ID == tAdmin.Person_ID && admin.Salary == tAdmin.Salary);
                    return Created(new Uri(Request.RequestUri + "/" + tempAdmin.Admin_ID), tempAdmin);
                }
                else
                {
                    return BadRequest("Data already exist!");
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
                ADMIN ad = db.ADMINs.First(admin => admin.Admin_ID == id);
                db.ADMINs.Remove(ad);
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
