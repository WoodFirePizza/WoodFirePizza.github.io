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

    public class PersonController : ApiController
    {
        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.People != null)
            {
                return Ok(db.People);
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
                PERSON p = db.People.First(person => person.Person_ID == id);
                return Ok(p);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]PERSON tPerson)
        {
            PERSON p;
            try
            {
                p = db.People.First(person => person.Person_ID == id);
                //p = tPerson;
         
                p.Forename = tPerson.Forename;
                p.Surname = tPerson.Surname;
                p.DOB = tPerson.DOB;
                p.Email = tPerson.Email;
                p.Mobile_Number = tPerson.Mobile_Number;
                p.Town_City = tPerson.Town_City;
                p.County = tPerson.County;
                p.Postcode = tPerson.Postcode;
                p.Address2 = tPerson.Address2;
                p.Address1 = tPerson.Address1;

                db.SaveChanges();
                return Ok(p);
            }
            catch(Exception e)
            {
                try
                {
                    db.People.Add(tPerson);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tPerson);
                }
                catch(Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
            
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]PERSON tPerson)
        {
            if(tPerson != null)
            {
                db.People.Add(tPerson);
                db.SaveChanges();
                PERSON tempPerson = db.People.First(person => person.Forename == tPerson.Forename
                                                        && person.Surname == tPerson.Surname
                                                        && person.DOB == tPerson.DOB
                                                        && person.Email == tPerson.Email
                                                        && person.Mobile_Number == tPerson.Mobile_Number
                                                        && person.Town_City == tPerson.Town_City
                                                        && person.County == tPerson.County
                                                        && person.Postcode == tPerson.Postcode
                                                        && person.Address1 == tPerson.Address1
                                                        && person.Address2 == tPerson.Address2);
                return Created(new Uri(Request.RequestUri + "/" + tempPerson.Person_ID), tempPerson);
            }
            else
            {
                return BadRequest("Invalid request body!");
            }
        }

        [HttpDelete]
        public IHttpActionResult Delete(string id)
        {
            try {
                PERSON p = db.People.First(person => person.Person_ID == id);
                db.People.Remove(p);
                db.SaveChanges();
                return Content(HttpStatusCode.NoContent, "Deleted - " + id.ToString());
            }
            catch(Exception e)
            {
                return NotFound();
            }
            
        }
    }
}
