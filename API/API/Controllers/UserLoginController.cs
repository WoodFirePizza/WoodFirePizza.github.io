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
    public class UserLoginController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        
        [HttpGet] //[Authorize(Roles = "Admin, Chef")]
        public IHttpActionResult Get()
        {
            if (db.UserLogins != null)
            {
                return Ok(db.UserLogins);
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
                UserLogin x = db.UserLogins.First(ul => ul.ID == id);
                return Ok(x);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]UserLogin tUserLogin)
        {
            UserLogin x;
            try
            {
                x = db.UserLogins.First(ul => ul.ID == id);
                x.Person_ID = tUserLogin.Person_ID;
                x.Username = tUserLogin.Username;
                x.Password = tUserLogin.Password;
                db.SaveChanges();
                return Ok(x);
            }
            catch (Exception e)
            {
                try
                {
                    db.UserLogins.Add(tUserLogin);
                    db.SaveChanges();
                    return (IHttpActionResult)Request.CreateResponse(HttpStatusCode.Created);
                    //return Created(new Uri(Request.RequestUri.ToString()), tUserLogin);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]UserLogin tUserLogin)
        {
            if (tUserLogin != null)
            {
                db.UserLogins.Add(tUserLogin);
                db.SaveChanges();
                UserLogin tempUser = db.UserLogins.First(user => user.Person_ID == tUserLogin.Person_ID
                                                            && user.Username == tUserLogin.Username
                                                            && user.Password == tUserLogin.Password);
                return Created(new Uri(Request.RequestUri + "/" + tempUser.ID.ToString()), tempUser);
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
                UserLogin x = db.UserLogins.First(ul => ul.ID == id);
                db.UserLogins.Remove(x);
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
