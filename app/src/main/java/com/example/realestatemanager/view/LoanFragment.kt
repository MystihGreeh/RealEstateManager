package com.example.realestatemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentLoanBinding
import java.text.NumberFormat



class LoanFragment : Fragment() {

    private var bindingLoan: FragmentLoanBinding? = null
    private val binding get() = bindingLoan!!

    private lateinit var currencyFormat: NumberFormat

    companion object {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingLoan = FragmentLoanBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }




    override fun onResume() {
        super.onResume()
        bindingLoan?.fragmentLoan
    }

    override fun onPause() {
        super.onPause()
        bindingLoan?.fragmentLoan
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingLoan?.fragmentLoan
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setOnClickListener()
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    private fun setOnClickListener(){
        bindingLoan?.calculate?.setOnClickListener { calculate() }
    }


    // ---------------------
    // ACTION
    // ---------------------

    private fun calculate(){
        val canCalculate: Boolean
        val amount = bindingLoan?.loanAmount?.text.toString().toDoubleOrNull() ?: 0.0
        val downPayment = bindingLoan?.downPaymentLayout?.text.toString().toDoubleOrNull() ?: 0.0
        val term = bindingLoan?.loanTerms?.text.toString().toDoubleOrNull()
        val interest = bindingLoan?.interestRate?.text.toString().toDoubleOrNull()
        val contribution = bindingLoan?.bringAmount?.text.toString().toDoubleOrNull() ?: 0.0

        when{
            bindingLoan?.loanAmount?.text.isNullOrEmpty() || bindingLoan?.loanTerms?.text.isNullOrEmpty() || bindingLoan?.interestRate?.text.isNullOrEmpty() || downPayment >= amount -> {
                canCalculate = false
                if (bindingLoan?.loanAmount?.text.isNullOrEmpty()){
                    bindingLoan?.loanAmountLayout?.error = resources.getString(R.string.required_field)
                }
                if (bindingLoan?.loanTerms?.text.isNullOrEmpty()){
                    bindingLoan?.loanTermsLayout?.error = resources.getString(R.string.required_field)
                }
                if (bindingLoan?.interestRate?.text.isNullOrEmpty()){
                    bindingLoan?.interestRateLayout?.error = resources.getString(R.string.required_field)
                }else if (interest!! < 0 || interest > 100){
                    bindingLoan?.interestRateLayout?.error = resources.getString(R.string.required_field)
                }
            }
            else -> {
                canCalculate = true
                bindingLoan?.loanAmountLayout?.error = null
                bindingLoan?.loanTermsLayout?.error = null
                bindingLoan?.interestRateLayout?.error = null
            }
        }

        if (canCalculate){
            val result: Double
            val totalInterest: Double
            val annualPayement: Double
            val downAmount: Double
            if(interest == 0.0){
                result = ((amount - contribution) - (downPayment)) / (term!! * 12)
                totalInterest = 0.0
                annualPayement = result * 12
                downAmount = (result * 12) * term
            }else{
                result = ((amount - contribution) - (downPayment)) * ((interest!! / (100)) / (12)) / (1 - Math.pow( 1 + ((interest / 100) / 12), -term!! *12))
                annualPayement = result * 12
                downAmount = (result * 12) * term
                totalInterest = downAmount - amount
            }
            bindingLoan?.monthlyPaymentLayout?.setText(String.format("%.2f",result), TextView.BufferType.EDITABLE)
            bindingLoan?.annualPaymentLayout?.setText(String.format("%.2f",annualPayement), TextView.BufferType.EDITABLE)
            bindingLoan?.downPaymentLayout?.setText(String.format("%.2f",downAmount), TextView.BufferType.EDITABLE)
            bindingLoan?.interestTotalCostLayout?.setText(String.format("%.2f",(totalInterest), TextView.BufferType.EDITABLE))


        }
    }


}