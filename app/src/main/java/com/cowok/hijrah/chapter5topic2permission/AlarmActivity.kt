package com.cowok.hijrah.chapter5topic2permission

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cowok.hijrah.chapter5topic2permission.databinding.ActivityAlarmBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        binding.buttonSelect.setOnClickListener {
            showTimePicker()
        }

        binding.buttonSet.setOnClickListener {
            setAlarm()
        }

        binding.buttonCancel.setOnClickListener {
            cancelAlarm()
        }

    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager.cancel(pendingIntent)
        binding.textJam.text = "00 : 00 AM"
        Toast.makeText(this, "Alarm Dibatalkan", Toast.LENGTH_SHORT).show()
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Toast.makeText(this, "Alarm Berhasil Diatur", Toast.LENGTH_SHORT).show()
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Tentukan Waktu Alarm")
            .build()

        picker.show(supportFragmentManager, "foxandroid")
        picker.addOnPositiveButtonClickListener {
            binding.textJam.text = if (picker.hour > 12){
                String.format("%02d", picker.hour - 12)+ " : "+ String.format(
                    "%02d", picker.minute
                )+ " PM"
            } else {
                String.format("%02d", picker.hour)+ " : "+ String.format(
                    "%02d", picker.minute
                )+ " AM"
            }

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "foxandroidReminderChannel"
            val description = "Channel For Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("foxandroid", name, importance)

            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}