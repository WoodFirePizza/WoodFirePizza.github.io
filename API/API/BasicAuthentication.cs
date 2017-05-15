using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;
using System.Net;
using System.Text;
using API.Models;
using System.Threading;
using System.Security.Principal;

namespace API
{
    public class BasicAuthentication : AuthorizationFilterAttribute
    {
        private PizzaStoreEntities db = new PizzaStoreEntities();

        public override void OnAuthorization(HttpActionContext actionContext)
        {
            
            if(actionContext.Request.Headers.Authorization == null)
            {
                actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.Unauthorized);
            }
            else
            {
                string token = actionContext.Request.Headers.Authorization.Parameter;
                string decodedToken = Encoding.UTF8.GetString(Convert.FromBase64String(token));
                string[] logInDetails = decodedToken.Split(':');
                string username = logInDetails[0];
                string password = logInDetails[1];

                string[] roles = {"Admin"};
                //deny access if invalid credentials 
                if ( !(username == "PRCS" && password == "251O") )
                {
                    
                    
                    
                    if ( (db.UserLogins.Any(usern => usern.Username == username && usern.Password == password)) )
                    {
                        UserLogin user = db.UserLogins.First(x => x.Username == username);
                        roles = getUserRole(user);
        
                    }
                    else
                    {
                        actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.Unauthorized);
                    }
                    
                    //actionContext.Response = actionContext.Request.CreateResponse(HttpStatusCode.
                }
                IPrincipal principal = new GenericPrincipal(new GenericIdentity("uhmm"), roles);
                Thread.CurrentPrincipal = principal;
                HttpContext.Current.User = principal;

            }// end else 
        }// end OnAuthorization

        private string[] getUserRole(UserLogin ul)
        {
            List<string> roles = new List<string>();
            
            
            if( db.ADMINs.Any(x => x.Person_ID == ul.Person_ID) )
            {
                roles.Add("Admin");
                
            }

            if( db.DRIVERs.Any(x => x.Person_ID == ul.Person_ID) )
            {
                roles.Add("Driver");
            }

            if(db.MEMBERs.Any(x => x.Person_ID == ul.Person_ID) )
            {
                roles.Add("Member");
            }

            if(db.CHEFs.Any(x => x.Person_ID == ul.Person_ID) )
            {
                roles.Add("Chef");
            }


            return roles.ToArray();
        }


    }// end class
}