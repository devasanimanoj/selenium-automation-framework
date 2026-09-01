package com.example.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * Common Keyboard Shortcuts with Actions
 * 
 * CTRL + A - Select All
 * CTRL + C - Copy
 * CTRL + V - Paste
 * CTRL + X - Cut
 * SHIFT + A - Shift + Key combination
 */
public class KeyboardShortcutsReference {

    /**
     * CTRL + A (Select All)
     */
    public static void performCtrlA(Actions actions) {
        actions.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();
    }

    /**
     * CTRL + C (Copy)
     */
    public static void performCtrlC(Actions actions) {
        actions.keyDown(Keys.CONTROL)
                .sendKeys("c")
                .keyUp(Keys.CONTROL)
                .perform();
    }

    /**
     * CTRL + V (Paste)
     */
    public static void performCtrlV(Actions actions) {
        actions.keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();
    }

    /**
     * CTRL + X (Cut)
     */
    public static void performCtrlX(Actions actions) {
        actions.keyDown(Keys.CONTROL)
                .sendKeys("x")
                .keyUp(Keys.CONTROL)
                .perform();
    }

    /**
     * SHIFT + Key
     */
    public static void performShiftKey(Actions actions, CharSequence key) {
        actions.keyDown(Keys.SHIFT)
                .sendKeys(key)
                .keyUp(Keys.SHIFT)
                .perform();
    }

    /**
     * ALT + Key
     */
    public static void performAltKey(Actions actions, CharSequence key) {
        actions.keyDown(Keys.ALT)
                .sendKeys(key)
                .keyUp(Keys.ALT)
                .perform();
    }
}
