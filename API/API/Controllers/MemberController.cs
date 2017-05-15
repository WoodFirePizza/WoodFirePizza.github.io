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
    public class MemberController : ApiController
    {

        private PizzaStoreEntities db = new PizzaStoreEntities();

        [HttpGet]
        public IHttpActionResult Get()
        {
            if(db.MEMBERs != null)
            {
                return Ok(db.MEMBERs);
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
                MEMBER m = db.MEMBERs.First(member => member.Member_ID == id);
                return Ok(m);
            }
            catch (Exception e)
            {
                return NotFound();
            }
        }

        [HttpPut]
        public IHttpActionResult Put(string id, [FromBody]MEMBER tMember)
        {
            MEMBER m;
            try
            {
                m = db.MEMBERs.First(member => member.Member_ID == id);
                m.Person_ID = tMember.Person_ID;
                m.Reward_Points = tMember.Reward_Points;
                db.SaveChanges();
                return Ok(m);
            }
            catch (Exception e)
            {
                try
                {
                    db.MEMBERs.Add(tMember);
                    db.SaveChanges();
                    return Created(new Uri(Request.RequestUri.ToString()), tMember);
                }
                catch (Exception er)
                {
                    return BadRequest(er.Message);
                }
            }
        }

        [HttpPost]
        public IHttpActionResult Post([FromBody]MEMBER tMember)
        {
            if (tMember != null)
            {
                if (!(db.MEMBERs.Any(member => member.Person_ID == tMember.Person_ID))) {
                    db.MEMBERs.Add(tMember);
                    db.SaveChanges();
                    MEMBER tempMember = db.MEMBERs.First(member => member.Person_ID == tMember.Person_ID
                                                        && member.Reward_Points == tMember.Reward_Points);
                    return Created(new Uri(Request.RequestUri + "/" + tempMember.Member_ID), tempMember);
                } else
                {
                    return BadRequest("Member already exists!");
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
                MEMBER m = db.MEMBERs.First(member => member.Member_ID == id);
                db.MEMBERs.Remove(m);
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
