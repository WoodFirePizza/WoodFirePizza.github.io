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
    using System.Runtime.Serialization;

    [DataContract]
    public partial class DRIVER
    {
        [DataMember]
        public string Driver_ID { get; set; }
        [DataMember]
        public string Person_ID { get; set; }
        [DataMember]
        public Nullable<decimal> Salary { get; set; }
        [DataMember]
        public string IsLoggedIn { get; set; }
        [DataMember]
        public string Valid_License { get; set; }
        [DataMember]
        public string Vehicle_Type { get; set; }
    
        public virtual PERSON PERSON { get; set; }
    }
}