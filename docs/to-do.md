# Frontend To-Do Items

## High Priority

### **Styling System**
**Status:** Not Started  
**Problem:** All styling is inline - looks unprofessional and hard to maintain

**What needs to be done:**
- Replace inline styles with proper CSS framework
- Make it look professional and consistent
- Add responsive design
- Consider dark mode

**Recommended approach:** Install Vuetify
```bash
npm install vuetify @nuxtjs/vuetify
```

**Files to change:**
- `nuxt.config.ts` - add Vuetify module
- All page files - replace inline styles with Vuetify components

### **Enhanced Loading States**
**Status:** Partially Completed  
**Problem:** Basic loading states exist but could be enhanced with spinners

**What's been done:**
- ✅ Basic loading states for form submissions
- ✅ Delete button shows "Deleting..." text
- ✅ Form buttons show "Creating..." / "Updating..." text
- ✅ Buttons are disabled during operations

**What still needs to be done:**
- Add loading spinners instead of just text
- Add skeleton loaders for better perceived performance
- Enhance loading states in table (currently just text)

## Medium Priority

### **API Type Verification**
**Status:** Needs Verification  
**Problem:** Frontend types may need updates to match backend DTOs

**What was done:**
- Backend now returns `ProjectDto` without audit fields
- Backend uses proper DTOs (`ProjectCreateDto`, `ProjectUpdateDto`)
- API contract is now consistent and follows RFC7807 standards

**Files that may need updates:**
- `types/project.ts` - verify it matches backend DTOs
- All page files - ensure no references to audit fields

## Implementation Notes

### **Styling System Priority**
- This is the main remaining task for visual polish
- Vuetify installation would provide biggest visual impact
- Would enable responsive design and dark mode
- All styling currently remains inline

### **Loading States Enhancement**
- Current text-based loading works but could be more polished
- Spinners and skeleton loaders would improve perceived performance
- Minor improvements needed, not critical functionality

### **Next Steps**
1. Fix npm issues (if any)
2. Install Vuetify: `npm install vuetify @nuxtjs/vuetify`
3. Convert pages to use Vuetify components
4. Add enhanced loading states (spinners/skeleton loaders)
5. Verify frontend types match backend DTOs

The frontend architecture is now solid with proper component structure, validation, and error handling. The remaining work is primarily visual polish and minor UX enhancements.
