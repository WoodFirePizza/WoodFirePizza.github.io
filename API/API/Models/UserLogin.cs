//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace API.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class UserLogin
    {
        public string ID { get; set; }
        public string Person_ID { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
    
        public virtual PERSON PERSON { get; set; }
    }
}