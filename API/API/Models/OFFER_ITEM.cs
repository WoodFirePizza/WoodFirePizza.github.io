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
    
    public partial class OFFER_ITEM
    {
        public string Offer_Item_ID { get; set; }
        public string Offer_ID { get; set; }
        public string Menu_ID { get; set; }
    
        public virtual OFFER OFFER { get; set; }
    }
}
