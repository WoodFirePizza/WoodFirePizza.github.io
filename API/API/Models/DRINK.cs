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
    
    public partial class DRINK
    {
        public string Drink_ID { get; set; }
        public string Menu_ID { get; set; }
        public string Size { get; set; }
    
        public virtual MENU MENU { get; set; }
    }
}