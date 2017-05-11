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
    public class DeliveryAddressController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if (db.DELIVERY_ADDRESS != null)
            {
                return Ok(db.DELIVERY_ADDRESS);
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
                DELIVERY_ADDRESS address = db.DELIVERY_ADDRESS.First(da => da.Delivery_Address_ID == id);
                return Ok(address);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }


        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]DELIVERY_ADDRESS tAddress)
        {
            DELIVERY_ADDRESS a;
            try
            {
                a = db.DELIVERY_ADDRESS.First(da => da.Delivery_Address_ID == id);

                a.Member_ID = tAddress.Member_ID;
                a.Address1 = tAddress.Address1;
                a.Address2 = tAddress.Address2;
                a.Town_City = tAddress.Town_City;
                a.County = tAddress.County;
                a.Postcode = tAddress.Postcode;

                db.SaveChanges();
                return Ok(a);
            }
            catch (Exception e)
            {
                try
                {
                    db.DELIVERY_ADDRESS.Add(tAddress);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tAddress);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }

        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]DELIVERY_ADDRESS tAddress)
        {
            if (tAddress != null)
            {
                db.DELIVERY_ADDRESS.Add(tAddress);
                db.SaveChanges();
                DELIVERY_ADDRESS tempDA = db.DELIVERY_ADDRESS.First(deladdr => deladdr.Member_ID == tAddress.Member_ID
                                            && deladdr.Address1 == tAddress.Address1
                                            && deladdr.Address2 == tAddress.Address2
                                            && deladdr.Town_City == tAddress.Town_City
                                            && deladdr.County == tAddress.County
                                            && deladdr.Postcode == tAddress.County);
                return Created(new Uri(Request.RequestUri + "/" + tempDA.Delivery_Address_ID), tempDA);
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
                DELIVERY_ADDRESS ad = db.DELIVERY_ADDRESS.First(a => a.Delivery_Address_ID == id);
                db.DELIVERY_ADDRESS.Remove(ad);
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
