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
    public class StockController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.STOCKs != null)
            {
                return Ok(db.STOCKs);
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
                STOCK s = db.STOCKs.First(stock => stock.Stock_ID == id);
                return Ok(s);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]STOCK tStock)
        {
            STOCK s;
            try
            {
                s = db.STOCKs.First(stock => stock.Stock_ID == id);
                s.Stock_Name = tStock.Stock_Name;
                s.Quantity = tStock.Quantity;
                s.Cost = tStock.Cost;
                db.SaveChanges();
                return Ok(s);
            }
            catch (Exception e)
            {
                try
                {
                    db.STOCKs.Add(tStock);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tStock);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]STOCK tStock)
        {
            if (tStock != null)
            {
                db.STOCKs.Add(tStock);
                db.SaveChanges();
                STOCK tempStock = db.STOCKs.First(stock => stock.Stock_Name == tStock.Stock_Name
                                                    && stock.Quantity == tStock.Quantity
                                                    && stock.Cost == tStock.Cost);
                return Created(new Uri(Request.RequestUri + "/" + tempStock.Stock_ID), tempStock);
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
                STOCK s = db.STOCKs.First(stock => stock.Stock_ID == id);
                db.STOCKs.Remove(s);
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
