```markdown
# Design System Document: High-End Neo-Brutalism for Productivity

## 1. Overview & Creative North Star: "The Editorial Engine"

This design system moves away from the polite, soft-edged aesthetics of modern SaaS to embrace a "Creative North Star" we call **The Editorial Engine**. It is a visual manifestation of high-output productivity—unapologetic, structural, and architecturally sound. 

While the core of Neo-Brutalism is often chaotic, this system elevates the style to a "High-End Editorial" experience. We achieve this through **Intentional Asymmetry** and **Structural Weight**. By using a strict 3px stroke and hard shadows, we create a UI that feels physically constructed rather than digitally rendered. Every element must feel like a deliberate block of intent, stacked within a high-contrast environment.

---

## 2. Colors & Surface Logic

The palette is a high-octane collection of vibrant hues balanced against a sophisticated cream base. 

### Core Palette
- **Background (Base):** `#FFFBF0` (Cream)
- **Primary:** `#FFE135` (Yellow)
- **Accent High-Contrast:** `#FF6B6B` (Coral), `#4ECDC4` (Teal), `#A29BFE` (Purple)
- **Feedback:** `#FF3B3B` (Error/Red), `#55EFC4` (Success/Mint), `#FF9A3C` (Warning/Orange)
- **Ink:** `#1A1A1A` (Black)

### The "No-Blur" Depth Rule
Unlike standard systems that use gaussian blurs for depth, this system utilizes **Hard Shadow Offsets**. Depth is a binary state: an object is either on the page or lifted above it. 
- Use `5px 5px 0px #1A1A1A` for standard interactive elements.
- Use `8px 8px 0px #1A1A1A` for high-priority modals or "Hero" cards.

### Surface Hierarchy & Nesting
To avoid visual clutter, use the `surface-container` tiers to create hierarchy through background shifts:
- **Level 0 (App Canvas):** `#FFFBF0`
- **Level 1 (Card/Section):** Transparent (defined by 3px border) or Primary Yellow.
- **Level 2 (In-Card Elements):** Subtle shifts to Mint or Teal for categorized tasks.

---

## 3. Typography: Space Grotesk

We utilize **Space Grotesk** for its monospaced-adjacent personality, which reinforces the "Productivity OS" feel.

- **Display (900 Weight):** Used for task counts and page titles. Large, imposing, and kerned tightly (-2%).
- **Headlines (700 Weight):** Used for section headers. Always paired with a 3px bottom border or a geometric ornament.
- **Body (700 Weight):** All body text is bold by default. In this system, "Regular" weight does not exist. Clarity is achieved through size and color contrast, not weight thinning.

**Scale:**
- `display-lg`: 3.5rem / Leading 1.1 (Caps strictly for Short Labels)
- `headline-md`: 1.75rem / Leading 1.2
- `title-sm`: 1.0rem / Leading 1.4
- `label-md`: 0.75rem / Letter Spacing +0.05rem (For metadata)

---

## 4. Elevation & Structural Layering

In "The Editorial Engine," we do not use 1px lines or soft shadows. Structure is dictated by the **3px Stroke** and **Tonal Blocking**.

- **The Stacking Principle:** When nesting containers (e.g., a task item inside a project card), do not use a border on the inner element if the outer element already has one. Instead, use a background color shift (e.g., Pink card on a Cream background).
- **Hard-Edge Glassmorphism:** For floating menus, use a semi-transparent White (`rgba(255, 251, 240, 0.8)`) with a `backdrop-filter: blur(10px)`. However, it *must* still be contained within a 3px black border with a 5px hard shadow. This creates a "Premium Brutalist" look—mixing raw structure with modern depth.
- **Asymmetric Offsets:** Forbid perfect centering in headers. Align titles to the top-left and place decorative stars or triangles in the bottom-right of the container to create dynamic tension.

---

## 5. Components

### Buttons (The Interaction Anchor)
- **Style:** 3px solid #1A1A1A border, Flat Fill (Yellow or Coral).
- **Shadow:** `5px 5px 0px #1A1A1A`.
- **Hover State:** `transform: translate(-2px, -2px); box-shadow: 7px 7px 0px #1A1A1A;`.
- **Active State:** `transform: translate(2px, 2px); box-shadow: 1px 1px 0px #1A1A1A;`.

### Input Fields
- **Style:** 3px black border, Cream background.
- **Focus State:** Background shifts to Teal (#4ECDC4) or Yellow (#FFE135).
- **Text:** Always Space Grotesk 700. Placeholder text should be Black at 50% opacity.

### Progress Cards
- **Forbid:** Standard thin loading bars.
- **Instead:** Use "Block Progress." A 3px bordered rectangle divided into segments. Completed segments are filled with Teal; incomplete are Cream.

### Geometric Ornaments
- Every view must include at least one "Signature Shape" (Star, Circle, or 45-degree Triangle) in a high-contrast color (Purple or Orange). These are not interactive; they act as "Visual Anchors" for the eye.

---

## 6. Do’s and Don’ts

### Do:
- **Embrace White Space:** Because the borders are heavy (3px), you must increase your padding scale (minimum 24px) to let the content breathe.
- **Use Intentional Overlaps:** Allow shadows of top-layer elements to fall visibly onto lower-layer elements to prove the "Hard Shadow" logic.
- **Use Color as Data:** Assign Teal to "Done," Coral to "Urgent," and Purple to "Deep Work."

### Don’t:
- **No 1px Lines:** Ever. If a divider is needed, use a 3px line or a 16px vertical gap.
- **No Border Radius > 4px:** The system must feel "architectural." Rounded corners are for "soft" apps; this is an OS for grinding.
- **No Blurs:** Avoid CSS `box-shadow` blur values or `filter: blur()` unless specifically used in the "Hard-Edge Glassmorphism" exception for floating modals.
- **No Centering Everything:** Avoid the "Bootstrap look." Use left-aligned typography and right-aligned decorative elements.

---

## Director’s Final Note
This system is designed to feel like a high-end physical planner crossed with a 1990s mainframe. It is high-contrast because productivity requires focus. It is heavy because work has weight. Stick to the 3px rule—the consistency of the stroke is what separates "amateur" neo-brutalism from a premium, intentional design system.```