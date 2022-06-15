package com.bbi.bizbulls.data.adaptermodel

import com.bbi.bizbulls.data.adaptermodel.Employee
import java.util.ArrayList

class ManageLeads(var location: String, employees: List<Employee>) {
    var employees: List<Employee> = ArrayList()

    init {
        this.employees = employees
    }
}