package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AttendanceController {

	// 'static' means this number is shared by EVERYONE who visits the site.
    private static int visitCount = 0;

    @GetMapping("/")
    public String home(Model model) {
        // Increment count when someone opens the home page
        visitCount++;
        
        // Send the count to the HTML
        model.addAttribute("visitCount", visitCount);
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("total") int currentTotal,
            @RequestParam("absent") int absent,
            @RequestParam(value = "futureTotal", required = false, defaultValue = "0") int futureTotal,
            @RequestParam(value = "delegation", required = false, defaultValue = "0") int delegation,
            @RequestParam(value = "target", defaultValue = "90") double targetPercent,
            Model model) {

        // --- 1. NEW MATH: DELEGATION IS A REWARD ---
        // Physical Attendance = Total - Absent
        // Academic Attendance = Physical + Bonus (Delegation)
        int physicalAttended = currentTotal - absent;
        int effectiveAttended = physicalAttended + delegation; // CHANGED: Now we ADD it

        // Cap: You can't have more attendance than total classes (usually)
        // But for calculation safety, we allow it, or you can cap it at currentTotal.
        if (effectiveAttended > currentTotal) effectiveAttended = currentTotal; 
        if (effectiveAttended < 0) effectiveAttended = 0;

        // --- 2. PERCENTAGE CALCULATION ---
        double currentPercentage = 0.0;
        if (currentTotal > 0) {
            currentPercentage = ((double) effectiveAttended / currentTotal) * 100;
        }

        String currentMsg;
        String statusColor;
        double targetDecimal = targetPercent / 100.0;

        // --- 3. STATUS LOGIC ---
        if (currentPercentage >= targetPercent) {
            int bunkable = (int) ((effectiveAttended / targetDecimal) - currentTotal);
            currentMsg = String.format("You are SAFE! You missed %d classes, but with %d bonus points, you can still bunk %d MORE.", absent, delegation, bunkable);
            statusColor = "green";
        } else {
            int need = (int) Math.ceil(((targetDecimal * currentTotal) - effectiveAttended) / (1.0 - targetDecimal));
            if (need < 0) need = 0;
            currentMsg = String.format("Danger! You missed %d classes. Even with %d bonus points, you need %d consecutive classes.", absent, delegation, need);
            statusColor = "red";
        }

        // --- 4. FUTURE PREDICTION ---
        String futureMsg = null;
        if (futureTotal > currentTotal) {
            int remainingClasses = futureTotal - currentTotal;
            int totalNeededByEnd = (int) Math.ceil(futureTotal * targetDecimal);
            int moreNeeded = totalNeededByEnd - effectiveAttended;

            if (moreNeeded <= 0) {
                futureMsg = "Prediction: You are completely safe for the semester! 🥳";
            } else if (moreNeeded > remainingClasses) {
                futureMsg = String.format("Prediction: IMPOSSIBLE. You need %d more, but only %d classes remain. 💀", moreNeeded, remainingClasses);
                statusColor = "red";
            } else {
                int futureBunkable = remainingClasses - moreNeeded;
                futureMsg = String.format("Prediction: Out of %d remaining classes, you CAN BUNK %d of them! 😎", remainingClasses, futureBunkable);
            }
        }

        // --- 5. SEND DATA ---
        model.addAttribute("currentPercent", String.format("%.2f", currentPercentage));
        model.addAttribute("currentMsg", currentMsg);
        model.addAttribute("futureMsg", futureMsg);
        model.addAttribute("statusColor", statusColor);
        
        model.addAttribute("total", currentTotal);
        model.addAttribute("absent", absent);
        model.addAttribute("delegation", delegation);
        model.addAttribute("futureTotal", futureTotal);
        model.addAttribute("target", targetPercent);

        return "index";
    }
}