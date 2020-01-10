package com.toedter.components;

import org.junit.jupiter.api.Test;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class JSpinFieldBeanInfoTest {
    @Test
    void name() {
        try {
            BeanInfo spinFieldBeanInfo = Introspector.getBeanInfo(JSpinField.class);
            BeanDescriptor beanDescriptor = spinFieldBeanInfo.getBeanDescriptor();
            assertNotNull(beanDescriptor);
            assertEquals(JSpinField.class.getSimpleName(), beanDescriptor.getName());
            assertEquals(JSpinField.class, beanDescriptor.getBeanClass());
            assertNull(spinFieldBeanInfo.getAdditionalBeanInfo());
            PropertyDescriptor[] propertyDescriptors = spinFieldBeanInfo.getPropertyDescriptors();
            // System.out.println(Arrays.toString(propertyDescriptors));
            assertNotNull(propertyDescriptors);
            assertEquals(63, propertyDescriptors.length);

//            [java.beans.PropertyDescriptor
//                    [name=UI;
//                      shortDescription=The UI object that implements the Component's LookAndFeel.;
//                      hidden;
//                      values={
//                              expert=false;
//                              visualUpdate=true;
//                              hidden=true;
//                              enumerationValues=[Ljava.lang.Object;@4afd21c6; transient=true; required=false};
//                              bound;
//                              propertyType=class javax.swing.plaf.PanelUI;
//                              readMethod=public javax.swing.plaf.PanelUI javax.swing.JPanel.getUI();
//                              writeMethod=public void javax.swing.JPanel.setUI(javax.swing.plaf.PanelUI)],
//            java.beans.PropertyDescriptor[
//                    name=UIClassID;
//                    shortDescription=A string that specifies the name of the L&F class.;
//                    expert;
//                    values={
//                            expert=true;
//                            visualUpdate=false;
//                            hidden=false;
//                            enumerationValues=[Ljava.lang.Object;@4d0753c9;
//                            required=false
//                    };
//                    propertyType=class java.lang.String;
//                    readMethod=public java.lang.String javax.swing.JPanel.getUIClassID()
//            ]
//            , java.beans.PropertyDescriptor[
//                    name=accessibleContext; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@1416a80a; required=false}; propertyType=class javax.
//            accessibility.AccessibleContext; readMethod=public javax.accessibility.AccessibleContext javax.swing.JPanel.getAccessibleContext()], java.beans.PropertyDescriptor[name=actionMap; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@719bb3b4; required=false}; bound; propertyType=class javax.swing.ActionMap; readMethod=public final javax.swing.ActionMap javax.swing.JComponent.getActionMap(); writeMethod=public final void javax.swing.JComponent.setActionMap(javax.swing.ActionMap)], java.beans.PropertyDescriptor[name=alignmentX; shortDescription=The preferred horizontal alignment of the component.; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@52cb4f50; required=false}; bound; propertyType=float; readMethod=public float javax.swing.JComponent.getAlignmentX(); writeMethod=public void javax.swing.JComponent.setAlignmentX(float)], java.beans.PropertyDescriptor[name=alignmentY; shortDescription=The preferred vertical alignment of the component.; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@25a5c7db; required=false}; bound; propertyType=float; readMethod=public float javax.swing.JComponent.getAlignmentY(); writeMethod=public void javax.swing.JComponent.setAlignmentY(float)], java.beans.PropertyDescriptor[name=ancestorListeners; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@4d27d9d; required=false}; propertyType=class [Ljavax.swing.event.AncestorListener;; readMethod=public javax.swing.event.AncestorListener[] javax.swing.JComponent.getAncestorListeners()], java.beans.PropertyDescriptor[name=autoscrolls; shortDescription=Determines if this component automatically scrolls its contents when dragged.; expert; values={expert=true; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@28f878a0; required=false}; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.getAutoscrolls(); writeMethod=public void javax.swing.JComponent.setAutoscrolls(boolean)], java.beans.PropertyDescriptor[name=background; shortDescription=The background color of the component.; preferred; values={expert=false; visualUpdate=true; hidden=false; enumerationValues=[Ljava.lang.Object;@20411320; transient=true; required=false}; bound; propertyType=class java.awt.Color; readMethod=public java.awt.Color java.awt.Component.getBackground(); writeMethod=public void javax.swing.JComponent.setBackground(java.awt.Color)], java.beans.PropertyDescriptor[name=baselineResizeBehavior; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@2b5183ec; required=false}; propertyType=class java.awt.Component$BaselineResizeBehavior; readMethod=public java.awt.Component$BaselineResizeBehavior javax.swing.JComponent.getBaselineResizeBehavior()], java.beans.PropertyDescriptor[name=border; shortDescription=The component's border.; preferred; values={expert=false; visualUpdate=true; hidden=false; enumerationValues=[Ljava.lang.Object;@3c782d8e; required=false}; bound; propertyType=interface javax.swing.border.Border; readMethod=public javax.swing.border.Border javax.swing.JComponent.getBorder(); writeMethod=public void javax.swing.JComponent.setBorder(javax.swing.border.Border)], java.beans.IndexedPropertyDescriptor[name=component; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@572e6fd9; required=false}; bound; indexedPropertyType=class java.
//            awt.Component; indexedReadMethod=public java.awt.Component java.awt.Container.getComponent(int)
//            ],
//            java.beans.PropertyDescriptor[
//                    name=componentCount;
//                    values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@7f5eae0f; required=false}; bound; propertyType=int; readMethod=public int java.awt.Container.getComponentCount()], java.beans.PropertyDescriptor[name=componentPopupMenu; shortDescription=Popup to show; preferred; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@58b71ceb; required=false}; bound; propertyType=class javax.swing.JPopupMenu; readMethod=public javax.swing.JPopupMenu javax.swing.JComponent.getComponentPopupMenu(); writeMethod=public void javax.swing.JComponent.setComponentPopupMenu(javax.swing.JPopupMenu)], java.beans.PropertyDescriptor[name=components; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@255e5e2e; required=false}; bound; propertyType=class [Ljava.awt.Component;; readMethod=public java.awt.Component[] java.awt.Container.getComponents()], java.beans.PropertyDescriptor[name=containerListeners; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@12abdfb; required=false}; bound; propertyType=class [Ljava.awt.event.ContainerListener;; readMethod=public synchronized java.awt.event.ContainerListener[] java.awt.Container.getContainerListeners()], java.beans.PropertyDescriptor[name=debugGraphicsOptions; shortDescription=Diagnostic options for graphics operations.; preferred; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@b0e5507; required=false}; propertyType=int; readMethod=public int javax.swing.JComponent.getDebugGraphicsOptions(); writeMethod=public void javax.swing.JComponent.setDebugGraphicsOptions(int)], java.beans.PropertyDescriptor[name=doubleBuffered; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@6bbe50c9; required=false}; bound; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.isDoubleBuffered(); writeMethod=public void javax.swing.JComponent.setDoubleBuffered(boolean)], java.beans.PropertyDescriptor[name=enabled; shortDescription=The enabled state of the component.; preferred; expert; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@3c46dcbe; required=false}; bound; propertyType=boolean; readMethod=public boolean java.awt.Component.isEnabled(); writeMethod=public void com.toedter.components.JSpinField.setEnabled(boolean)], java.beans.PropertyDescriptor[name=focusCycleRoot; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@68577ba8; required=false}; bound; propertyType=boolean; readMethod=public boolean java.awt.Container.isFocusCycleRoot(); writeMethod=public void java.awt.Container.setFocusCycleRoot(boolean)], java.beans.IndexedPropertyDescriptor[name=focusTraversalKeys; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@1108adc8; required=false}; bound; indexedPropertyType=interface java.util.Set; indexedReadMethod=public java.util.Set java.awt.Container.getFocusTraversalKeys(int); indexedWriteMethod=public void javax.swing.JComponent.setFocusTraversalKeys(int,java.util.Set)], java.beans.PropertyDescriptor[name=focusTraversalPolicy; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@8a98f38; required=false}; bound; propertyType=class java.awt.FocusTraversalPolicy; readMethod=public java.awt.FocusTraversalPolicy java.awt.Container.getFocusTraversalPolicy(); writeMethod=public void java.awt.Container.setFocusTraversalPolicy(java.awt.FocusTraversalPolicy)], java.beans.PropertyDescriptor[name=focusTraversalPolicyProvider; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@20011bf; required=false}; bound; propertyType=boolean; readMethod=public final boolean java.awt.Container.isFocusTraversalPolicyProvider(); writeMethod=public final void java.awt.Container.setFocusTraversalPolicyProvider(boolean)], java.beans.PropertyDescriptor[name=focusTraversalPolicySet; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@51d9b06c; required=false}; bound; propertyType=boolean; readMethod=public boolean java.awt.Container.isFocusTraversalPolicySet()], java.beans.PropertyDescriptor[name=focusable; bound; propertyType=boolean; readMethod=public boolean java.awt.Component.isFocusable(); writeMethod=public void java.awt.Component.setFocusable(boolean)], java.beans.PropertyDescriptor[name=font; shortDescription=The font for the component.; preferred; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@5eb2172; transient=true; required=false}; bound; propertyType=class java.awt.Font; readMethod=public java.awt.Font java.awt.Component.getFont(); writeMethod=public void com.toedter.components.JSpinField.setFont(java.awt.Font)], java.beans.PropertyDescriptor[name=foreground; shortDescription=The foreground color of the component.; preferred; values={expert=false; visualUpdate=false; hidden=false; transient=true; enumerationValues=[Ljava.lang.Object;@41ffaeb8; required=false}; bound; propertyType=class java.awt.Color; readMethod=public java.awt.Color java.awt.Component.getForeground(); writeMethod=public void com.toedter.components.JSpinField.setForeground(java.awt.Color)], java.beans.PropertyDescriptor[name=graphics; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@43f0c2d1; required=false}; propertyType=class java.awt.Graphics; readMethod=public java.awt.Graphics javax.swing.JComponent.getGraphics()], java.beans.PropertyDescriptor[name=height; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@5fb65013; required=false}; propertyType=int; readMethod=public int javax.swing.JComponent.getHeight()], java.beans.PropertyDescriptor[name=horizontalAlignment; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@38a1a26; required=false}; bound; propertyType=int; writeMethod=public void com.toedter.components.JSpinField.setHorizontalAlignment(int)], java.beans.PropertyDescriptor[name=inheritsPopupMenu; shortDescription=Whether or not the JPopupMenu is inherited; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@3fbcfe81; required=false}; bound; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.getInheritsPopupMenu(); writeMethod=public void javax.swing.JComponent.setInheritsPopupMenu(boolean)], java.beans.PropertyDescriptor[name=inputMap; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@7a1f45ed; required=false}; bound; propertyType=class javax.swing.InputMap; readMethod=public final javax.swing.InputMap javax.swing.JComponent.getInputMap()], java.beans.PropertyDescriptor[name=inputVerifier; shortDescription=The component's input verifier.; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@1744a475; required=false}; bound; propertyType=class javax.swing.InputVerifier; readMethod=public javax.swing.InputVerifier javax.swing.JComponent.getInputVerifier(); writeMethod=public void javax.swing.JComponent.setInputVerifier(javax.swing.InputVerifier)], java.beans.PropertyDescriptor[name=insets; expert; values={expert=true; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@444cc791; required=false}; bound; propertyType=class java.awt.Insets; readMethod=public java.awt.Insets javax.swing.JComponent.getInsets()], java.beans.PropertyDescriptor[name=layout; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@1c5c616f; required=false}; bound; propertyType=interface java.awt.LayoutManager; readMethod=public java.awt.LayoutManager java.awt.Container.getLayout(); writeMethod=public void java.awt.Container.setLayout(java.awt.LayoutManager)], java.beans.PropertyDescriptor[name=managingFocus; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@1c6c6f24; required=false}; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.isManagingFocus()], java.beans.PropertyDescriptor[name=maximum; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@2eb917d0; required=false}; bound; propertyType=int; readMethod=public int com.toedter.components.JSpinField.getMaximum(); writeMethod=public void com.toedter.components.JSpinField.setMaximum(int)], java.beans.PropertyDescriptor[name=maximumSize; shortDescription=The maximum size of the component.; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@c6b2dd9; transient=true; required=false}; bound; propertyType=class java.awt.Dimension; readMethod=public java.awt.Dimension javax.swing.JComponent.getMaximumSize(); writeMethod=public void javax.swing.JComponent.setMaximumSize(java.awt.Dimension)], java.beans.PropertyDescriptor[name=minimum; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@73437222; required=false}; bound; propertyType=int; readMethod=public int com.toedter.components.JSpinField.getMinimum(); writeMethod=public void com.toedter.components.JSpinField.setMinimum(int)], java.beans.PropertyDescriptor[name=minimumSize; shortDescription=The minimum size of the component.; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@ca93621; transient=true; required=false}; bound; propertyType=class java.awt.Dimension; readMethod=public java.awt.Dimension javax.swing.JComponent.getMinimumSize(); writeMethod=public void javax.swing.JComponent.setMinimumSize(java.awt.Dimension)], java.beans.PropertyDescriptor[name=name; bound; propertyType=class java.lang.String; readMethod=public java.lang.String java.awt.Component.getName(); writeMethod=public void java.awt.Component.setName(java.lang.String)], java.beans.PropertyDescriptor[name=nextFocusableComponent; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@6a48a7f3; required=false}; bound; propertyType=class java.awt.Component; readMethod=public java.awt.Component javax.swing.JComponent.getNextFocusableComponent(); writeMethod=public void javax.swing.JComponent.setNextFocusableComponent(java.awt.Component)], java.beans.PropertyDescriptor[name=opaque; shortDescription=The component's opacity; expert; values={expert=true; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@3f985a86; required=false}; bound; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.isOpaque(); writeMethod=public void javax.swing.JComponent.setOpaque(boolean)], java.beans.PropertyDescriptor[name=optimizedDrawingEnabled; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@57a2ed35; required=false}; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.isOptimizedDrawingEnabled()], java.beans.PropertyDescriptor[name=paintingForPrint; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@12ffd1de; required=false}; propertyType=boolean; readMethod=public final boolean javax.swing.JComponent.isPaintingForPrint()], java.beans.PropertyDescriptor[name=paintingTile; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@3d278b4d; required=false}; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.isPaintingTile()], java.beans.PropertyDescriptor[name=preferredSize; shortDescription=The preferred size of the component.; preferred; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@4096aa05; transient=true; required=false}; bound; propertyType=class java.awt.Dimension; readMethod=public java.awt.Dimension javax.swing.JComponent.getPreferredSize(); writeMethod=public void javax.swing.JComponent.setPreferredSize(java.awt.Dimension)], java.beans.PropertyDescriptor[name=registeredKeyStrokes; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@9d3c67; required=false}; propertyType=class [Ljavax.swing.KeyStroke;; readMethod=public javax.swing.KeyStroke[] javax.swing.JComponent.getRegisteredKeyStrokes()], java.beans.PropertyDescriptor[name=requestFocusEnabled; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@6c806c8b; required=false}; bound; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.isRequestFocusEnabled(); writeMethod=public void javax.swing.JComponent.setRequestFocusEnabled(boolean)], java.beans.PropertyDescriptor[name=rootPane; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@6dfcffb5; required=false}; propertyType=class javax.swing.JRootPane; readMethod=public javax.swing.JRootPane javax.swing.JComponent.getRootPane()], java.beans.PropertyDescriptor[name=spinner; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@184fb68d; required=false}; bound; propertyType=class java.awt.Component; readMethod=public java.awt.Component com.toedter.components.JSpinField.getSpinner()], java.beans.PropertyDescriptor[name=toolTipText; shortDescription=The text to display in a tool tip.; preferred; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@71d8cfe7; required=false}; propertyType=class java.lang.String; readMethod=public java.lang.String javax.swing.JComponent.getToolTipText(); writeMethod=public void javax.swing.JComponent.setToolTipText(java.lang.String)], java.beans.PropertyDescriptor[name=topLevelAncestor; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@1e530163; required=false}; propertyType=class java.awt.Container; readMethod=public java.awt.Container javax.swing.JComponent.getTopLevelAncestor()], java.beans.PropertyDescriptor[name=transferHandler; shortDescription=Mechanism for transfer of data to and from the component; hidden; values={expert=false; visualUpdate=false; hidden=true; enumerationValues=[Ljava.lang.Object;@14d8444b; required=false}; bound; propertyType=class javax.swing.TransferHandler; readMethod=public javax.swing.TransferHandler javax.swing.JComponent.getTransferHandler(); writeMethod=public void javax.swing.JComponent.setTransferHandler(javax.swing.TransferHandler)], java.beans.PropertyDescriptor[name=validateRoot; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@71466383; required=false}; bound; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.isValidateRoot()], java.beans.PropertyDescriptor[name=value; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@46d63dbb; required=false}; bound; propertyType=int; readMethod=public int com.toedter.components.JSpinField.getValue(); writeMethod=public void com.toedter.components.JSpinField.setValue(int)], java.beans.PropertyDescriptor[name=verifyInputWhenFocusTarget; shortDescription=Whether the Component verifies input before accepting focus.; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@4088741b; required=false}; bound; propertyType=boolean; readMethod=public boolean javax.swing.JComponent.getVerifyInputWhenFocusTarget(); writeMethod=public void javax.swing.JComponent.setVerifyInputWhenFocusTarget(boolean)], java.beans.PropertyDescriptor[name=vetoableChangeListeners; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@16a49a5d; required=false}; propertyType=class [Ljava.beans.VetoableChangeListener;; readMethod=public synchronized java.beans.VetoableChangeListener[] javax.swing.JComponent.getVetoableChangeListeners()], java.beans.PropertyDescriptor[name=visible; hidden; values={expert=false; visualUpdate=true; hidden=true; enumerationValues=[Ljava.lang.Object;@54bca971; transient=true; required=false}; bound; propertyType=boolean; readMethod=public boolean java.awt.Component.isVisible(); writeMethod=public void javax.swing.JComponent.setVisible(boolean)], java.beans.PropertyDescriptor[name=visibleRect; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@23706db8; required=false}; propertyType=class java.awt.Rectangle; readMethod=public java.awt.Rectangle javax.swing.JComponent.getVisibleRect()], java.beans.PropertyDescriptor[name=width; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@205bed61; required=false}; propertyType=int; readMethod=public int javax.swing.JComponent.getWidth()], java.beans.PropertyDescriptor[name=x; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@129fed45; required=false}; propertyType=int; readMethod=public int javax.swing.JComponent.getX()], java.beans.PropertyDescriptor[name=y; values={expert=false; visualUpdate=false; hidden=false; enumerationValues=[Ljava.lang.Object;@23592946; required=false}; propertyType=int; readMethod=public int javax.swing.JComponent.getY()]
//            ]
        } catch (IntrospectionException e) {
            fail("could not create bean info for JSpinField", e);
        }
    }
}