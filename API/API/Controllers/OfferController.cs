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
    public class OfferController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.OFFERs != null)
            {
                return Ok(db.OFFERs);
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
                OFFER o = db.OFFERs.First(offer => offer.Offer_ID == id);
                return Ok(o);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]OFFER tOffer)
        {
            OFFER o;
            try
            {
                o = db.OFFERs.First(offer => offer.Offer_ID == id);
                o.Total_Price = tOffer.Total_Price;
                o.Start_Date = tOffer.Start_Date;
                o.End_Date = tOffer.End_Date;
                o.Description = tOffer.Description;
                db.SaveChanges();
                return Ok(o);
            }
            catch (Exception e)
            {
                try
                {
                    db.OFFERs.Add(tOffer);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tOffer);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]OFFER tOffer)
        {
            if (tOffer != null)
            {
                db.OFFERs.Add(tOffer);
                db.SaveChanges();
                OFFER tempOffer = db.OFFERs.First(offer => offer.Total_Price == tOffer.Total_Price
                                                    && offer.Start_Date == tOffer.Start_Date
                                                    && offer.End_Date == tOffer.End_Date
                                                    && offer.Description == tOffer.Description);
                return Created(new Uri(Request.RequestUri + "/" + tempOffer.Offer_ID), tempOffer);
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
                OFFER o = db.OFFERs.First(offer => offer.Offer_ID == id);
                db.OFFERs.Remove(o);
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
