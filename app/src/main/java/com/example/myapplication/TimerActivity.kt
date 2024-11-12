package com.example.myapplication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TimerActivity : AppCompatActivity() {

    private var timeSelected : Int = 0
    private var timeCountDown: CountDownTimer? = null
    private var timeProgress = 0
    private var pauseOffSet: Long = 0
    private var isStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer)

        val addBtn: ImageButton = findViewById(R.id.btnAdd)
        addBtn.setOnClickListener {
            setTimeFunction()
        }
        val startBtn: Button = findViewById(R.id.btnPlayPause)
        startBtn.setOnClickListener {
            startTimerSetup()
        }

        val resetBtn:ImageButton = findViewById(R.id.ib_reset)
        resetBtn.setOnClickListener {
            resetTime()
        }

        val addTimeTv:TextView = findViewById(R.id.tv_addTime)
        addTimeTv.setOnClickListener {
            addExtraTime()
        }

        val conti=findViewById<AppCompatButton>(R.id.addCart)
        conti.setOnClickListener {
            val Intent = Intent(this, ThankyouActivity::class.java)
            startActivity(Intent)
        }

    }

    private fun addExtraTime()
    {
        val progressBar :ProgressBar = findViewById(R.id.pbTimer)
        if(timeSelected!=0)
        {
            timeSelected+=15
            progressBar.max = timeSelected
            timePause()
            startTimer(pauseOffSet)
            Toast.makeText(this, "15 sec addes", Toast.LENGTH_SHORT).show()

        }
    }

    private fun resetTime()
    {
        if (timeCountDown!= null)
        {
            timeCountDown!!.cancel()
            timeProgress=0
            timeSelected=0
            pauseOffSet=0
            timeCountDown=null
            val startBtn:Button = findViewById(R.id.btnPlayPause)
            startBtn.text ="Start"
            isStart = true
            val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
            progressBar.progress = 0
            val timeLeftTv: TextView = findViewById(R.id.tvTimeLeft)

            timeLeftTv.text = "0"
        }
    }

    private fun timePause()
    {
        if (timeCountDown!=null)
        {
            timeCountDown!!.cancel()
        }
    }

    private fun startTimerSetup()
    {
        val startBtn: Button = findViewById(R.id.btnPlayPause)
        if(timeSelected>timeProgress)
        {
            if (isStart)
            {
                startBtn.text = "Pause"
                startTimer(pauseOffSet)
                isStart = false
            }
            else{
                isStart = true
                startBtn.text = "Resume"
                timePause()
            }
        }
        else
        {
            Toast.makeText(this, "Enter Time", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer(pauseOffsetL: Long) {
        val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
        progressBar.progress = timeProgress
        timeCountDown = object : CountDownTimer(
            (timeSelected * 1000).toLong() - pauseOffsetL * 1000, 1000)
        {
            override fun onTick(p0: Long) {
                timeProgress++
                pauseOffSet = timeSelected.toLong() - p0 / 1000
                progressBar.progress = timeSelected-timeProgress
                val timeLeftTv: TextView = findViewById(R.id.tvTimeLeft)
                timeLeftTv.text = (timeSelected - timeProgress).toString()
            }

            override fun onFinish() {
                resetTime()
                Toast.makeText(this@TimerActivity, "Times Up! Adding Cart Process is completed", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun setTimeFunction() {
        val timeDialog = Dialog(this)
        timeDialog.setContentView(R.layout.add_dialog)
        val timeSet = timeDialog.findViewById<EditText>(R.id.etGetTime)
        val timeLeftTv: TextView = findViewById(R.id.tvTimeLeft)
        val btnStart: Button = findViewById(R.id.btnPlayPause)
        val progressBar = findViewById<ProgressBar>(R.id.pbTimer)
        timeDialog.findViewById<Button>(R.id.btnOk).setOnClickListener {
            if (timeSet.text.isEmpty())
            {
                Toast.makeText(this, "Enter Time Duration", Toast.LENGTH_SHORT).show()
            } else {
                resetTime()
                timeLeftTv.text = timeSet.text
                btnStart.text = "Start"
                timeSelected = timeSet.text.toString().toInt()
                progressBar.max = timeSelected
            }
            timeDialog.dismiss()
        }
        timeDialog.show()
    }

    override fun onDestroy(){
        super.onDestroy()
        if(timeCountDown!=null)
        {
            timeCountDown?.cancel()
            timeProgress=0
        }
    }
}