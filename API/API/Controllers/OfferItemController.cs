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
    public class OfferItemController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.OFFER_ITEM != null)
            {
                return Ok(db.OFFER_ITEM);
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
                OFFER_ITEM oi = db.OFFER_ITEM.First(oitem => oitem.Offer_Item_ID == id);
                return Ok(oi);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]OFFER_ITEM tOitem)
        {
            OFFER_ITEM oi;
            try
            {
                oi = db.OFFER_ITEM.First(oitem => oitem.Offer_Item_ID == id);
                oi.Offer_ID = tOitem.Offer_ID;
                oi.Menu_ID = tOitem.Menu_ID;
                db.SaveChanges();
                return Ok(oi);
            }
            catch (Exception e)
            {
                try
                {
                    db.OFFER_ITEM.Add(tOitem);
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
        public IHttpActionResult Post([FromBody]OFFER_ITEM tOitem)
        {
            if (tOitem != null)
            {
                db.OFFER_ITEM.Add(tOitem);
                db.SaveChanges();
                OFFER_ITEM tempOfferItem = db.OFFER_ITEM.First(offerItem => offerItem.Offer_ID == tOitem.Offer_ID
                                                                    && offerItem.Menu_ID == tOitem.Menu_ID);
                return Created(new Uri(Request.RequestUri + "/" + tempOfferItem.Offer_Item_ID), tempOfferItem);
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
                OFFER_ITEM p = db.OFFER_ITEM.First(oitem => oitem.Offer_Item_ID == id);
                db.OFFER_ITEM.Remove(p);
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
