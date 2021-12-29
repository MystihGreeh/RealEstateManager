package com.example.realestatemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentLoanBinding
import java.text.NumberFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [LoanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoanFragment : Fragment() {

    private var bindingLoan: FragmentLoanBinding? = null
    private val binding get() = bindingLoan!!

    private lateinit var currencyFormat: NumberFormat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingLoan = FragmentLoanBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingLoan = null
    }

    private fun setOnClickListener() {
        binding.calculate.setOnClickListener {
            val amount = binding.loanAmount.text.toString()
            val interest = binding.interestRate.text.toString()
            val nbYears = binding.loanTerms.text.toString()


            var isDataOk = true

            if (amount.isBlank()) {
                binding.loanAmount.error = getString(R.string.required_field)
                isDataOk = false
            }

            if (interest.isBlank()) {
                binding.interestRate.error = getString(R.string.required_field)
                isDataOk = false
            }

            if (nbYears.isBlank()) {
                binding.loanTerms.error = getString(R.string.required_field)
                isDataOk = false
            }

            if (isDataOk) {
                binding.loanAmount.setText(currencyFormat.format(amount.toDouble()))
                val number = NumberFormat.getNumberInstance(Locale.getDefault())
                binding.interestRate.setText(number.format(interest.toDouble()))
                binding.loanTerms.setText(nbYears)
                //calculate(amount.toDouble(), interest.toDouble(), nbYears.toInt())
            }
        }
    }

    /*private fun calculate(amount: Double, interest: Double, nbMonths: Int) {
        val monthlyInterestRate = ((((1 + (interest / 100)).pow(1.0 / 12.0) - 1) * 100) / 100)
        val t1N = 1 + monthlyInterestRate
        val result = ((finalAmount * monthlyInterestRate) * ((t1N).pow(nbMonths))) / (((t1N).pow(nbMonths)) - 1)

        binding.txtResult.text = getString(R.string.monthly_payment, currencyFormat.format(result))

        val view = this.currentFocus
        if (view != null) {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }*/


}