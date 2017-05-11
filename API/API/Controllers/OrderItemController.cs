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
    public class OrderItemController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.ORDER_ITEM != null)
            {
                return Ok(db.ORDER_ITEM);
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
                
                   ORDER_ITEM oi = db.ORDER_ITEM.First(oitem => oitem.Order_Item_ID == id);
                   return Ok(oi);
                
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [Route("api/orderitem/order/{orderID}")]
        [HttpGet]
        public IHttpActionResult FindOrderItemsByOrdeID(string orderID)
        {
            try
            {

                //ORDER_ITEM oi = db.ORDER_ITEM.First(oitem => oitem.Order_Item_ID == id);
                return Ok(db.ORDER_ITEM.Where(x => x.Order_ID == orderID));

            }
            catch (Exception e)
            {
                return NotFound();
            }
        }
        

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]ORDER_ITEM tOitem)
        {
            ORDER_ITEM oi;
            try
            {
                oi = db.ORDER_ITEM.First(oItem => oItem.Order_Item_ID == id);
                oi.Order_ID = tOitem.Order_ID;
                oi.Menu_ID = tOitem.Menu_ID;
                oi.Quantity = tOitem.Quantity;
                oi.Notes = tOitem.Notes;
                db.SaveChanges();
                return Ok(oi);
            }
            catch (Exception e)
            {
                try
                {
                    db.ORDER_ITEM.Add(tOitem);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tOitem);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]ORDER_ITEM tOitem)
        {
            if (tOitem != null)
            {
                db.ORDER_ITEM.Add(tOitem);
                db.SaveChanges();
                ORDER_ITEM tempOrderItem = db.ORDER_ITEM.First(orderitem => orderitem.Order_ID == tOitem.Order_ID
                                                                    && orderitem.Menu_ID == tOitem.Menu_ID
                                                                    && orderitem.Notes == tOitem.Notes
                                                                    && orderitem.Quantity == tOitem.Quantity);
                return Created(new Uri(Request.RequestUri + "/" + tempOrderItem.Order_Item_ID), tempOrderItem);
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
                ORDER_ITEM oi = db.ORDER_ITEM.First(oitem => oitem.Order_Item_ID == id);
                db.ORDER_ITEM.Remove(oi);
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
