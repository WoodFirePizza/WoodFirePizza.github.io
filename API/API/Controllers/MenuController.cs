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
    public class MenuController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.MENUs != null)
            {
                return Ok(db.MENUs);
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
                MENU m = db.MENUs.First(menu => menu.Menu_ID == id);
                return Ok(m);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]MENU tMenu)
        {
            MENU m;
            try
            {
                m = db.MENUs.First(menu => menu.Menu_ID == id);
                m.Name = tMenu.Name;
                m.Price = tMenu.Price;
                m.img_source = tMenu.img_source;
                m.Description = tMenu.Description; 
                db.SaveChanges();
                return Ok(m);
            }
            catch (Exception e)
            {
                try
                {
                    db.MENUs.Add(tMenu);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tMenu);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]MENU tMenu)
        {
            if (tMenu != null)
            {
                db.MENUs.Add(tMenu);
                db.SaveChanges();
                MENU tempMenu = db.MENUs.First(menu => menu.Name == tMenu.Name
                                                && menu.Price == tMenu.Price
                                                && menu.img_source == tMenu.img_source
                                                && menu.Description == tMenu.Description);
                return Created(new Uri(Request.RequestUri + "/" + tempMenu.Menu_ID), tempMenu);
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
                MENU m = db.MENUs.First(menu => menu.Menu_ID == id);
                db.MENUs.Remove(m);
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
