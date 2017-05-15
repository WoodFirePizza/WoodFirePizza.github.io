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
    public class TDeliveryAddressController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();


        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.TEMP_DELIVERYADDRESS != null)
            {
                return Ok(db.TEMP_DELIVERYADDRESS);
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
                TEMP_DELIVERYADDRESS tda = db.TEMP_DELIVERYADDRESS.First(tempda => tempda.T_DELIVERY_ID == id);
                return Ok(tda);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]TEMP_DELIVERYADDRESS tDA)
        {
            TEMP_DELIVERYADDRESS tda;
            try
            {
                tda = db.TEMP_DELIVERYADDRESS.First(x => x.T_DELIVERY_ID == id);
                tda.ADDRESS1 = tDA.ADDRESS1;
                tda.ADDRESS2 = tDA.ADDRESS2;
                tda.TOWN_CITY = tDA.TOWN_CITY;
                tda.COUNTY = tDA.COUNTY;
                tda.POSTCODE = tDA.POSTCODE;
                db.SaveChanges();
                return Ok(tda);
            }
            catch (Exception e)
            {
                try
                {
                    db.TEMP_DELIVERYADDRESS.Add(tDA);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tDA);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]TEMP_DELIVERYADDRESS tDA)
        {
            if (tDA != null)
            {
                if (!(db.TEMP_DELIVERYADDRESS.Any(x => x.T_DELIVERY_ID == tDA.T_DELIVERY_ID)))
                {
                    db.TEMP_DELIVERYADDRESS.Add(tDA);
                    db.SaveChanges();
                    TEMP_DELIVERYADDRESS tempTDA = db.TEMP_DELIVERYADDRESS.First(tda => tda.ADDRESS1 == tDA.ADDRESS1
                                                                         && tda.ADDRESS2 == tDA.ADDRESS2
                                                                         && tda.TOWN_CITY == tDA.TOWN_CITY
                                                                         && tda.COUNTY == tDA.COUNTY
                                                                         && tda.POSTCODE == tDA.POSTCODE);
                    return Created(new Uri(Request.RequestUri + "/" + tempTDA.T_DELIVERY_ID), tempTDA);
                } else
                {
                    return BadRequest("Data already exists!!");
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
                TEMP_DELIVERYADDRESS tda = db.TEMP_DELIVERYADDRESS.First(x => x.T_DELIVERY_ID == id);
                db.TEMP_DELIVERYADDRESS.Remove(tda);
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
