package com.example.adt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adt.R
import com.example.adt.databinding.ListItemEmployeeListBinding
import com.example.adt.model.Data


class EmployeeListAdapter :
    ListAdapter<Data, EmployeeListAdapter.EmployeeListViewHolder>(EMPLOYEE_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EmployeeListAdapter.EmployeeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemEmployeeListBinding.inflate(inflater, parent, false)
        return EmployeeListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EmployeeListAdapter.EmployeeListViewHolder,
        position: Int,
    ) {
        with(holder) {
            val employeeListItem = getItem(position)
            employeeListItem.let { holder.bind(it) }
            val currentItem = getItem(position)
            val isVisible = currentItem.visibility
            binding.salaryTv.visibility = if (isVisible) View.VISIBLE else View.GONE
            binding.root.setOnClickListener {
                currentItem.visibility = !currentItem.visibility
                notifyItemChanged(position)
            }
        }

    }

    inner class EmployeeListViewHolder(
        val binding: ListItemEmployeeListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeItem: Data) {
            with(binding) {
                profileImage.setImageResource(R.drawable.cartoon)
                idTv.text = buildString {
                    append("ID : ")
                    append(employeeItem.id)
                }
                nameTv.text = buildString {
                    append("Name : ")
                    append(employeeItem.employee_name)
                }
                salaryTv.text = buildString {
                    append("Salary : ")
                    append(employeeItem.employee_salary)
                }
            }

        }
    }

    companion object {
        private val EMPLOYEE_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem == newItem
        }
    }
}

