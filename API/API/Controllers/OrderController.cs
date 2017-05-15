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

    [EnableCors(origins:"*", methods:"*", headers:"*", exposedHeaders: "Location")]
    public class OrderController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();


        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.ORDERs != null)
            {
                return Ok(db.ORDERs);
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
                ORDER o = db.ORDERs.First(order => order.Order_ID == id);
      
                return Ok(o);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]ORDER tOrder)
        {
            ORDER o;
            try
            {
                o = db.ORDERs.First(order => order.Order_ID == id);
                o.Member_ID = tOrder.Member_ID;
                o.Driver_ID = tOrder.Driver_ID;
                o.Total_Price = tOrder.Total_Price;
                o.DELIVERYADDRESSID = tOrder.DELIVERYADDRESSID;
                o.TEMP_DELIVERYADDRESSID = tOrder.TEMP_DELIVERYADDRESSID;
                o.ISPAID = tOrder.ISPAID;
                o.Date = tOrder.Date;
                o.Time = tOrder.Time;
                o.IsDelivery = tOrder.IsDelivery;
                o.Status = tOrder.Status;
                db.SaveChanges();
                return Ok(o);
            }
            catch (Exception e)
            {
                try
                {
                    db.ORDERs.Add(tOrder);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tOrder);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]ORDER tOrder)
        {
            if (tOrder != null)
            {
                db.ORDERs.Add(tOrder);
                db.SaveChanges();
                ORDER temp = db.ORDERs.First(o => o.Member_ID == tOrder.Member_ID 
                                                && o.Date == tOrder.Date 
                                                && o.Time == tOrder.Time
                                                && o.Driver_ID == tOrder.Driver_ID 
                                                && o.Total_Price == tOrder.Total_Price
                                                && o.DELIVERYADDRESSID == tOrder.DELIVERYADDRESSID 
                                                && o.TEMP_DELIVERYADDRESSID == tOrder.TEMP_DELIVERYADDRESSID
                                                && o.ISPAID == tOrder.ISPAID
                                                && o.Status == tOrder.Status
                                                && o.IsDelivery == tOrder.IsDelivery);
                return Created(new Uri(Request.RequestUri + "/" + temp.Order_ID), temp);
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
                ORDER o = db.ORDERs.First(order => order.Order_ID == id);
                db.ORDERs.Remove(o);
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
